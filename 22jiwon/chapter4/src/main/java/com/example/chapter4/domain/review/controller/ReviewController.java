package com.example.chapter4.domain.review.controller;

import com.example.chapter4.domain.review.dto.ReviewResponseDto;
import com.example.chapter4.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.chapter4.global.apiPayload.ApiResponse;
import com.example.chapter4.global.apiPayload.code.GeneralSuccessCode;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/me")
    public ApiResponse<List<ReviewResponseDto>> getMyReviews(
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer rating) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, reviewService.getMyReviews(storeName, rating));
    }
    // 필요시 추가: 리뷰작성, 상세조회, 삭제 등
}