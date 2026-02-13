package com.example.chapter4.domain.store.controller;

import com.example.chapter4.domain.store.dto.StoreRequest;
import com.example.chapter4.domain.store.dto.StoreResponse;
import com.example.chapter4.domain.store.service.StoreService;
import com.example.chapter4.global.apiPayload.ApiResponse;
import com.example.chapter4.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stores")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @GetMapping
    public ApiResponse<List<StoreResponse>> getStores() {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, storeService.getStores());
    }

    @GetMapping("/{storeId}")
    public ApiResponse<StoreResponse> getStore(@PathVariable Long storeId) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, storeService.getStore(storeId));
    }

    @PostMapping
    public ApiResponse<StoreResponse> addStore(
            @RequestBody @Valid StoreRequest request
    ) {
        StoreResponse storeResponse = storeService.addStore(request);

        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, storeResponse);
    }
}