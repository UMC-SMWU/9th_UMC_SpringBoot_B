package com.example.chapter4.domain.region.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class RegionListResponseDto {
    private final List<RegionResponseDto> regions;
}
