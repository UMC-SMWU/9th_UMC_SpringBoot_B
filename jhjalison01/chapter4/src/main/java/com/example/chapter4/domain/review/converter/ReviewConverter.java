package com.example.chapter4.domain.review.converter;

import com.example.chapter4.domain.member.entity.Member;
import com.example.chapter4.domain.review.dto.req.ReviewRequestDto;
import com.example.chapter4.domain.review.entity.Review;
import com.example.chapter4.domain.store.entity.Store;

public class ReviewConverter {
    public static Review toReview(Member member, Store store, ReviewRequestDto.CreateDto request) {
        return Review.builder()
                .member(member)
                .store(store)
                .content(request.getContent())
                .rating(request.getRating())
                .build();
    }
}
