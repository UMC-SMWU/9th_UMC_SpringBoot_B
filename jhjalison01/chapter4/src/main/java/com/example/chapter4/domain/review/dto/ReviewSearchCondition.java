package com.example.chapter4.domain.review.dto;

import com.example.chapter4.global.annotation.CheckPage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;

@Data
public class ReviewSearchCondition {
    @Schema(description = "가게 ID (필터링)", example = "1")
    private Long storeId;   // 가게별 필터링
    @Schema(description = "페이지 번호 (1 이상)", example = "1")
    private Float rating; // 별점별 필터링

    @Schema(description = "페이지 번호 (1 이상)", example = "1", required = true)
    @CheckPage // 커스텀 어노테이션 적용
    private Integer page;
}
