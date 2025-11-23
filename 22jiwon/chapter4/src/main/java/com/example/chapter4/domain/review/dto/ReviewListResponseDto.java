package com.example.chapter4.domain.review.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ReviewListResponseDto {

    private final List<ReviewResponseDto> content;
    private final int page;
    private final int size;
    private final long totalElements;
    private final int totalPages;
}