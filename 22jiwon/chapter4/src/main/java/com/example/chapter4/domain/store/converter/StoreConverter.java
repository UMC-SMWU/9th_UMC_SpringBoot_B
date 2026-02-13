package com.example.chapter4.domain.store.converter;

import com.example.chapter4.domain.region.entity.Region;
import com.example.chapter4.domain.store.dto.StoreRequest;
import com.example.chapter4.domain.store.dto.StoreResponse;
import com.example.chapter4.domain.store.entity.Store;

public class StoreConverter {


    public static Store toStore(StoreRequest dto, Region region) {
        return Store.builder()
                .name(dto.getName())
                .detailAddress(dto.getDetailAddress())
                .managerNumber(dto.getManagerNumber())
                .region(region) // 연관관계 설정
                .build();
    }


    public static StoreResponse toStoreResponse(Store store) {
        return new StoreResponse(
                store.getId(),
                store.getName(),
                store.getManagerNumber(),
                store.getDetailAddress(),
                store.getRegion().getId()
        );
    }
}