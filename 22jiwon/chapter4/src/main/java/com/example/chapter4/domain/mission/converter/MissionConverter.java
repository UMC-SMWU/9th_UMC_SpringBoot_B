package com.example.chapter4.domain.mission.converter;

import com.example.chapter4.domain.mission.dto.MissionListResponseDto;
import com.example.chapter4.domain.mission.dto.MissionResponseDto;
import com.example.chapter4.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;

public class MissionConverter {

    public static MissionResponseDto toMissionResponseDto(Mission m) {
        return MissionResponseDto.builder()
                .id(m.getId())
                .name(m.getName())
                .description(m.getDescription())
                .pointAwarded(m.getPointAwarded())
                .deadline(m.getDeadline())
                .storeId(m.getStore().getId())
                .storeName(m.getStore().getName())
                .build();
    }

    public static MissionListResponseDto toMissionListResponseDto(Page<Mission> page) {
        return MissionListResponseDto.builder()
                .content(
                        page.getContent().stream()
                                .map(MissionConverter::toMissionResponseDto)
                                .toList()
                )
                .page(page.getNumber() + 1)   // 다시 1-based
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();
    }
}