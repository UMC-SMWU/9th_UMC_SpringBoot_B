package com.example.chapter4.domain.review.repository;

import com.example.chapter4.domain.review.dto.res.ReviewResDto;
import com.example.chapter4.domain.review.dto.ReviewSearchCondition;
import com.example.chapter4.domain.review.entity.Review;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewQueryDsl {

    //리뷰 검색
    List<Review> searchReview(
            Predicate predicate
    );

    Page<Review> searchMemberReviews(Long memberId, ReviewSearchCondition condition, Pageable pageable);
}
