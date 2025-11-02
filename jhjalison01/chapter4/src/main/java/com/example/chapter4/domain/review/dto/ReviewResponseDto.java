package com.example.chapter4.domain.review.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewResponseDto {
    private Long reviewId;
    private String storeName;
    private String content;
    private int rating;
    private LocalDateTime createdAt;
}
