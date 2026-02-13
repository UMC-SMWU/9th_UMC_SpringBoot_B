package com.example.chapter4.domain.region.dto;

import com.example.chapter4.domain.region.entity.Region;
import lombok.Getter;

@Getter
public class RegionResponseDto {
    private final Long id;
    private final String name;

    public RegionResponseDto(Region region) {
        this.id = region.getId();
        this.name = region.getName();
    }
}
