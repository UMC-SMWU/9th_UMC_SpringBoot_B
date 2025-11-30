package com.example.chapter4.domain.review.controller;

import com.example.chapter4.domain.review.dto.req.ReviewRequestDto;
import com.example.chapter4.domain.review.dto.res.ReviewResDto;
import com.example.chapter4.domain.review.dto.ReviewSearchCondition;
import com.example.chapter4.domain.review.entity.Review;
import com.example.chapter4.domain.review.service.command.ReviewCommandService;
import com.example.chapter4.domain.review.service.query.ReviewQueryService;
import com.example.chapter4.global.apiPayLoad.ApiResponse;
import com.example.chapter4.global.apiPayLoad.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController implements ReviewControllerDocs{

    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

    @GetMapping("member/{memberId}")
    public ApiResponse<ReviewResDto.ReviewDetailListDto> getMyReviews(
            @PathVariable Long memberId,
            @Valid @ModelAttribute ReviewSearchCondition condition // Query Parameter를 DTO로 바인딩
    ) {

        ReviewResDto.ReviewDetailListDto reviews = reviewQueryService.getMemberReviews(memberId, condition);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK,reviews);
    }

    @PostMapping()
    public ApiResponse<ReviewResDto.CreateResultDto> createReview(@RequestBody ReviewRequestDto.CreateDto request) {
        Review review = reviewCommandService.createReview(request,1L); //memberId를 1로 하드코딩
        ReviewResDto.CreateResultDto result = ReviewResDto.CreateResultDto.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();

        return ApiResponse.onSuccess(GeneralSuccessCode.OK,result);
    }

    // 가게의 리뷰 목록 조회
    @GetMapping()
    public ApiResponse<ReviewResDto.ReviewPreViewListDto> getReviews(
            @RequestParam String storeName,
            @RequestParam(defaultValue = "1") Integer page
    ){

        //ReviewSuccessCode code = ReviewSuccessCode.FOUND;
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, reviewQueryService.findReview(storeName,page));
    }

}
