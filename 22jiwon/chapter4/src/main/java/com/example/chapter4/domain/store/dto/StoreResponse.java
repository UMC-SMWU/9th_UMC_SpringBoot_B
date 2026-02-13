package com.example.chapter4.domain.store.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StoreResponse {
    private Long id;
    private String name;
    private String managerNumber;
    private String detailAddress;
    private Long regionId;
}

