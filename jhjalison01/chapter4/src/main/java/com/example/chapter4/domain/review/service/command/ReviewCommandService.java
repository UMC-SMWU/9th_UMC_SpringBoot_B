package com.example.chapter4.domain.review.service.command;

import com.example.chapter4.domain.review.dto.req.ReviewRequestDto;
import com.example.chapter4.domain.review.entity.Review;

import java.util.List;

public interface ReviewCommandService {
    Review createReview(ReviewRequestDto.CreateDto requestDto, Long memberId);
}
