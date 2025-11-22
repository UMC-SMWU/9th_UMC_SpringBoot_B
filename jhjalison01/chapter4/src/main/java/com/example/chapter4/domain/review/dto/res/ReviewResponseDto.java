package com.example.chapter4.domain.review.dto.res;

import lombok.*;

import java.time.LocalDateTime;

@Data
public class ReviewResponseDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateResultDto {
        private Long reviewId;
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewDetailDto {
        private Long reviewId;
        private String storeName;
        private String content;
        private int rating;
        private LocalDateTime createdAt;
    }
}
