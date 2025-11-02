package com.example.chapter4.domain.mission.service;

import com.example.chapter4.domain.mission.dto.MissionListResponseDto;
import com.example.chapter4.domain.mission.dto.MissionResponseDto;
import com.example.chapter4.domain.mission.entity.Mission;
import com.example.chapter4.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;

    public MissionListResponseDto getMissions(int page, int size) {
        Page<Mission> missions = missionRepository.findAll(PageRequest.of(page, size));
        List<MissionResponseDto> content = missions.stream()
                .map(MissionResponseDto::new)
                .collect(Collectors.toList());
        return new MissionListResponseDto(content, missions.getNumber(), missions.getSize(), missions.getTotalElements(), missions.getTotalPages());
    }

    public MissionResponseDto getMission(Long missionId) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new IllegalArgumentException("미션 없음"));
        return new MissionResponseDto(mission);
    }
}
