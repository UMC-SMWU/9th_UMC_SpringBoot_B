package com.example.chapter4.domain.review.repository;

import com.example.chapter4.domain.review.dto.res.ReviewResDto;
import com.example.chapter4.domain.review.dto.ReviewSearchCondition;
import com.example.chapter4.domain.review.entity.Review;
import com.querydsl.core.types.Predicate;

import java.util.List;

public interface ReviewQueryDsl {

    //리뷰 검색
    List<Review> searchReview(
            Predicate predicate
    );

    List<ReviewResDto.ReviewDetailDto> searchMemberReviews(Long memberId, ReviewSearchCondition condition);
}
