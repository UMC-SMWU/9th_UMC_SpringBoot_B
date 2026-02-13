package com.example.chapter4.domain.review.repository;

import com.example.chapter4.domain.review.entity.Review;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.example.chapter4.domain.review.entity.QReview.review;
import static com.example.chapter4.domain.store.entity.QStore.store;
import static com.example.chapter4.domain.user.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Review> findMyReviewsDynamic(Long writerId, String storeName, Integer rating) {

        return queryFactory
                .selectFrom(review)
                .leftJoin(review.store, store).fetchJoin()
                .leftJoin(review.writer, user).fetchJoin()
                .where(
                        review.writer.id.eq(writerId),
                        storeNameEq(storeName),
                        ratingCondition(rating)
                )
                .orderBy(review.createdAt.desc())
                .fetch();
    }

    @Override
    public Page<Review> findMyReviewsPaged(Long writerId, String storeName, Integer rating, Pageable pageable) {

        List<Review> content = queryFactory
                .selectFrom(review)
                .leftJoin(review.store, store).fetchJoin()
                .leftJoin(review.writer, user).fetchJoin()
                .where(
                        review.writer.id.eq(writerId),
                        storeNameEq(storeName),
                        ratingCondition(rating)
                )
                .orderBy(review.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(review.count())
                .from(review)
                .leftJoin(review.store, store)
                .where(
                        review.writer.id.eq(writerId),
                        storeNameEq(storeName),
                        ratingCondition(rating)
                )
                .fetchOne();

        long totalElements = total == null ? 0L : total;

        return new PageImpl<>(content, pageable, totalElements);
    }

    private BooleanExpression storeNameEq(String storeName) {
        if (!StringUtils.hasText(storeName)) return null;
        return store.name.eq(storeName);
    }

    private BooleanExpression ratingCondition(Integer rating) {
        if (rating == null) return null;
        if (rating == 5) return review.rating.eq(5.0f);
        else if (rating == 4) return review.rating.goe(4.0f).and(review.rating.lt(5.0f));
        else if (rating == 3) return review.rating.goe(3.0f).and(review.rating.lt(4.0f));
        else if (rating == 2) return review.rating.goe(2.0f).and(review.rating.lt(3.0f));
        else if (rating == 1) return review.rating.goe(1.0f).and(review.rating.lt(2.0f));
        else return null;
    }
}