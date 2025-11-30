package com.example.chapter4.domain.review.repository;

import com.example.chapter4.domain.review.dto.res.ReviewResDto;
import com.example.chapter4.domain.review.dto.ReviewSearchCondition;
import com.example.chapter4.domain.review.entity.QReview;
import com.example.chapter4.domain.review.entity.Review;
import com.example.chapter4.domain.store.entity.QLocation;
import com.example.chapter4.domain.store.entity.QStore;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Review> searchReview(
            Predicate predicate
    ){

        //Q클래스 선언
        QReview review = QReview.review;
        QStore store = QStore.store;
        QLocation location = QLocation.location;

        return queryFactory
                .selectFrom(review)
                .leftJoin(store).on(store.id.eq(review.store.id))
                .leftJoin(location).on(location.id.eq(store.location.id))
                .where(predicate)
                .fetch();
    }

    @Override
    public Page<Review> searchMemberReviews(Long memberId, ReviewSearchCondition condition, Pageable pageable) {

        QReview review = QReview.review;
        QStore store = QStore.store;

        BooleanBuilder builder = new BooleanBuilder();

        // 내가 작성한 리뷰
        builder.and(review.member.id.eq(memberId));

        // 가게별
        if (condition.getStoreId() != null) {
            builder.and(store.id.eq(condition.getStoreId()));
        }

        // 별점별
        if (condition.getRating() != null) {
            builder.and(review.rating.eq(condition.getRating()));
        }

        List<Review> content = queryFactory
                .selectFrom(review)
                .join(review.store, store).fetchJoin() // N+1 문제 방지 (Review -> Store)
                .where(builder)
                .orderBy(review.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(review.count())
                .from(review)
                .leftJoin(review.store, store)
                .where(builder)
                .fetchOne();

        return new PageImpl<>(content, pageable, total !=null?total:0L);

    }
}
