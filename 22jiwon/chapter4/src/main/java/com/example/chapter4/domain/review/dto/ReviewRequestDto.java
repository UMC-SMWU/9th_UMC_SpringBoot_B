package com.example.chapter4.domain.review.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewRequestDto {
    private Float rating;
    private String content;
    private Long storeId;
}
