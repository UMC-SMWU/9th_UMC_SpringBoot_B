package com.example.chapter4.domain.store.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class StoreListResponse {
    private List<StoreResponse> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
}
