package com.example.chapter4.domain.review.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class ReviewRequestDto {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateDto {
        @NotNull(message = "별점 필수")
        private Float rating;
        @NotBlank(message = "리뷰 내용 필수")
        private String content;
        @NotNull(message = "가게ID 필수")
        private Long storeId;
    }
}
