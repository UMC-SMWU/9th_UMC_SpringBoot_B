package com.example.chapter4.domain.region.service;

import com.example.chapter4.domain.region.dto.RegionResponseDto;
import com.example.chapter4.domain.region.entity.Region;
import com.example.chapter4.domain.region.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegionService {

    private final RegionRepository regionRepository;

    // 전체 지역 리스트
    public List<RegionResponseDto> getAllRegions() {
        List<Region> regions = regionRepository.findAll();
        return regions.stream()
                .map(RegionResponseDto::new)
                .collect(Collectors.toList());
    }

    // 단일 조회
    public RegionResponseDto getRegion(Long id) {
        Region region = regionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("지역 없음"));
        return new RegionResponseDto(region);
    }
}
