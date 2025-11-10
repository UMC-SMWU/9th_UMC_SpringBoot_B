package com.example.chapter4.domain.review.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.example.chapter4.global.annotation.ExistStore;

@Getter
@NoArgsConstructor
public class ReviewRequestDto {
    @NotNull(message = "별점 필수")
    private Float rating;
    @NotBlank(message = "리뷰 내용 필수")
    private String content;
    @NotNull(message = "가게ID 필수")
    @ExistStore
    private Long storeId;
}

