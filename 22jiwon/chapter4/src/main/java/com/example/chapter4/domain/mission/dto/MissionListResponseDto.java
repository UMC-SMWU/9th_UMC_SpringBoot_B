package com.example.chapter4.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MissionListResponseDto {
    private final List<MissionResponseDto> content;
    private final int page;
    private final int size;
    private final long totalElements;
    private final int totalPages;
}
