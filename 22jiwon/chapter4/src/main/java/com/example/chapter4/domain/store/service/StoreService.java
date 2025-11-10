package com.example.chapter4.domain.store.service;

import com.example.chapter4.domain.region.entity.Region;
import com.example.chapter4.domain.region.repository.RegionRepository;
import com.example.chapter4.domain.store.converter.StoreConverter;
import com.example.chapter4.domain.store.dto.StoreRequest;
import com.example.chapter4.domain.store.entity.Store;
import com.example.chapter4.domain.store.repository.StoreRepository;
import com.example.chapter4.domain.store.dto.StoreResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    public List<StoreResponse> getStores() {
        return storeRepository.findAll().stream()
                .map(StoreConverter::toStoreResponse)
                .collect(Collectors.toList());
    }

    public StoreResponse getStore(Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("Store not found"));
        return StoreConverter.toStoreResponse(store);
    }

    @Transactional
    public StoreResponse addStore(StoreRequest request) {

        Region region = regionRepository.findById(request.getRegionId())
                .orElseThrow(() -> new RuntimeException("Region not found (Validation failed)"));

        Store newStore = StoreConverter.toStore(request, region);

        Store savedStore = storeRepository.save(newStore);

        return StoreConverter.toStoreResponse(savedStore);
    }
}