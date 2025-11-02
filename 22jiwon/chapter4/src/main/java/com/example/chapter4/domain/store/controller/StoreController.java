package com.example.chapter4.domain.store.controller;

import com.example.chapter4.domain.store.dto.StoreResponse;
import com.example.chapter4.domain.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stores")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @GetMapping
    public List<StoreResponse> getStores() {
        return storeService.getStores();
    }

    @GetMapping("/{storeId}")
    public StoreResponse getStore(@PathVariable Long storeId) {
        return storeService.getStore(storeId);
    }
}
