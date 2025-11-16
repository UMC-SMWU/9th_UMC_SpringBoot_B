package com.example.chapter4.domain.review.controller;

import com.example.chapter4.domain.review.dto.ReviewResponseDto;
import com.example.chapter4.domain.review.dto.ReviewSearchCondition;
import com.example.chapter4.domain.review.service.ReviewService;
import com.example.chapter4.global.apiPayLoad.ApiResponse;
import com.example.chapter4.global.apiPayLoad.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("member/{memberId}")
    public ApiResponse<List<ReviewResponseDto>> getMyReviews(
            @PathVariable Long memberId,
            @ModelAttribute ReviewSearchCondition condition // Query Parameter를 DTO로 바인딩
    ) {

        List<ReviewResponseDto> reviews = reviewService.getMemberReviews(memberId, condition);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK,reviews);
    }
}
