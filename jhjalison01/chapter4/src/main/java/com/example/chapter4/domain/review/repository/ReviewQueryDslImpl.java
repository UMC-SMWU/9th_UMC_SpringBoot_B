package com.example.chapter4.domain.review.repository;

import com.example.chapter4.domain.review.dto.ReviewResponseDto;
import com.example.chapter4.domain.review.dto.ReviewSearchCondition;
import com.example.chapter4.domain.review.entity.QReview;
import com.example.chapter4.domain.review.entity.Review;
import com.example.chapter4.domain.store.entity.QLocation;
import com.example.chapter4.domain.store.entity.QStore;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

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
    public List<ReviewResponseDto> searchMemberReviews(Long memberId, ReviewSearchCondition condition) {

        QReview review = QReview.review;
        QStore store = QStore.store;

        BooleanBuilder builder = new BooleanBuilder();

        // 필수: 내가 작성한 리뷰
        builder.and(review.member.id.eq(memberId));

        // 선택 필터: 가게별
        if (condition.getStoreId() != null) {
            builder.and(store.id.eq(condition.getStoreId()));
        }

        // 선택 필터: 별점별
        if (condition.getRating() != null) {
            builder.and(review.rating.eq(condition.getRating()));
        }

        return queryFactory
                .select(Projections.constructor(
                                ReviewResponseDto.class,
                                review.id,
                                review.store.id,
                                review.rating,
                                review.content,
                                review.createdAt
                        )
                )
                .leftJoin(review).on(review.store.id.eq(store.id))
                .where(builder)
                .orderBy(review.createdAt.desc()) // 최신순 정렬
                .fetch();

    }
}
