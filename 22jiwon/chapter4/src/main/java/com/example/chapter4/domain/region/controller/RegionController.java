package com.example.chapter4.domain.region.controller;

import com.example.chapter4.domain.region.dto.RegionResponseDto;
import com.example.chapter4.domain.region.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regions")
@RequiredArgsConstructor
public class RegionController {

    private final RegionService regionService;

    // 전체 조회
    @GetMapping
    public List<RegionResponseDto> getAllRegions() {
        return regionService.getAllRegions();
    }

    // 단일 조회 (선택)
    @GetMapping("/{regionId}")
    public RegionResponseDto getRegion(@PathVariable Long regionId) {
        return regionService.getRegion(regionId);
    }
}
