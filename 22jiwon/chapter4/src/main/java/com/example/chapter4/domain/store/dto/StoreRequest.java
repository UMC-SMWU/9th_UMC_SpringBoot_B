package com.example.chapter4.domain.store.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StoreRequest {
    private String name;
    private String managerNumber;
    private String detailAddress;
    private Long regionId;
}
