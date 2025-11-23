package com.example.chapter4.domain.review.dto.res;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReviewResDto {

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

    @Builder
    public record ReviewPreViewListDto(
            List<ReviewPreViewDto> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record ReviewPreViewDto(
            String ownerNickname,
            Float score,
            String body,
            LocalDate createdAt
    ){}
}
