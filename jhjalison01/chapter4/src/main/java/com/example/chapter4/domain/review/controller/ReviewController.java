package com.example.chapter4.domain.review.controller;

import com.example.chapter4.domain.review.dto.req.ReviewRequestDto;
import com.example.chapter4.domain.review.dto.res.ReviewResponseDto;
import com.example.chapter4.domain.review.dto.ReviewSearchCondition;
import com.example.chapter4.domain.review.entity.Review;
import com.example.chapter4.domain.review.service.command.ReviewCommandServiceImpl;
import com.example.chapter4.domain.review.service.query.ReviewQueryServiceImpl;
import com.example.chapter4.global.apiPayLoad.ApiResponse;
import com.example.chapter4.global.apiPayLoad.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewCommandServiceImpl reviewCommandServiceImpl;
    private final ReviewQueryServiceImpl reviewQueryServiceImpl;

    @GetMapping("member/{memberId}")
    public ApiResponse<List<ReviewResponseDto.ReviewDetailDto>> getMyReviews(
            @PathVariable Long memberId,
            @ModelAttribute ReviewSearchCondition condition // Query Parameter를 DTO로 바인딩
    ) {

        List<ReviewResponseDto.ReviewDetailDto> reviews = reviewQueryServiceImpl.getMemberReviews(memberId, condition);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK,reviews);
    }

    @PostMapping()
    public ApiResponse<ReviewResponseDto.CreateResultDto> createReview(@RequestBody ReviewRequestDto.CreateDto request) {
        Review review = reviewCommandServiceImpl.createReview(request,1L); //memberId를 1로 하드코딩
        ReviewResponseDto.CreateResultDto result = ReviewResponseDto.CreateResultDto.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();

        return ApiResponse.onSuccess(GeneralSuccessCode.OK,result);
    }

}
