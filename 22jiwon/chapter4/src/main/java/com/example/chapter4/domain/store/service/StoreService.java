package com.example.chapter4.domain.store.service;

import com.example.chapter4.domain.store.entity.Store;
import com.example.chapter4.domain.store.repository.StoreRepository;
import com.example.chapter4.domain.store.dto.StoreResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

    public List<StoreResponse> getStores() {
        return storeRepository.findAll().stream()
                .map(store -> new StoreResponse(
                        store.getId(),
                        store.getName(),
                        store.getManagerNumber(),
                        store.getDetailAddress(),
                        store.getRegion().getId()
                )).collect(Collectors.toList());
    }

    public StoreResponse getStore(Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("Store not found"));
        return new StoreResponse(
                store.getId(),
                store.getName(),
                store.getManagerNumber(),
                store.getDetailAddress(),
                store.getRegion().getId()
        );
    }
}
