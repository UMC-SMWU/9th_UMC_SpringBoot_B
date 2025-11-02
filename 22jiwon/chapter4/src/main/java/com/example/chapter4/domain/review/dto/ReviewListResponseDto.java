package com.example.chapter4.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

@Getter
@AllArgsConstructor
public class ReviewListResponseDto {
    private List<ReviewResponseDto> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
}
