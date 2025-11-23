package com.example.chapter4.domain.review.service.query;

import com.example.chapter4.domain.review.dto.ReviewSearchCondition;
import com.example.chapter4.domain.review.dto.res.ReviewResDto;
import com.example.chapter4.domain.review.entity.Review;

import java.util.List;

public interface ReviewQueryService {
    List<Review> searchReview(String query, String type);

    List<ReviewResDto.ReviewDetailDto> getMemberReviews(Long memberId, ReviewSearchCondition condition);

    ReviewResDto.ReviewPreViewListDto findReview(String storeName, Integer page);
}
