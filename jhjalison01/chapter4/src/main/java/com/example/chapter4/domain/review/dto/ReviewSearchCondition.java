package com.example.chapter4.domain.review.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class ReviewSearchCondition {
    private Long storeId;   // 가게별 필터링
    private Float rating; // 별점별 필터링
}
