package com.example.chapter4.domain.review.converter;

import com.example.chapter4.domain.review.dto.ReviewRequestDto;
import com.example.chapter4.domain.review.dto.ReviewResponseDto;
import com.example.chapter4.domain.review.entity.Review;
import com.example.chapter4.domain.user.entity.User;
import com.example.chapter4.domain.store.entity.Store;

public class ReviewConverter {

    // 엔티티 -> 응답 DTO 변환
    public static ReviewResponseDto toReviewResponse(Review review) {
        return new ReviewResponseDto(review);
    }

    // DTO -> 엔티티(생성용)
    public static Review toReview(ReviewRequestDto dto, User writer, Store store) {
        return Review.builder()
                .rating(dto.getRating())
                .content(dto.getContent())
                .writer(writer)
                .store(store)
                .build();
    }
}
