package com.example.chapter4.domain.review.controller;

import com.example.chapter4.domain.review.dto.ReviewSearchCondition;
import com.example.chapter4.domain.review.dto.res.ReviewResDto;
import com.example.chapter4.global.apiPayLoad.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.RequestParam;

public interface ReviewControllerDocs {
    @Operation(
            summary = "가게의 리뷰 목록 조회 API By 현정 (개발 중)",
            description = "특정 가게의 리뷰를 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    ApiResponse<ReviewResDto.ReviewPreViewListDto> getReviews(
            @RequestParam String storeName,
            @RequestParam Integer page
    );

    @Operation(summary = "사용자가 쓴 리뷰 목록 조회", description = "내가 작성한 리뷰 목록을 조회합니다. 페이징을 포함합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = ReviewResDto.ReviewDetailListDto.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "페이지 번호 오류 등",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "회원의 아이디"),
            @Parameter(name = "page", description = "페이지 번호 (1부터 시작)")
    })
    ApiResponse<ReviewResDto.ReviewDetailListDto> getMyReviews(
            Long memberId,
            ReviewSearchCondition condition
    );
}
