package com.example.chapter4.domain.mission.controller;

import com.example.chapter4.domain.mission.dto.MissionListResponseDto;
import com.example.chapter4.domain.mission.dto.MissionResponseDto;
import com.example.chapter4.domain.mission.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @GetMapping
    public MissionListResponseDto getMissions(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "20") int size) {
        return missionService.getMissions(page, size);
    }

    @GetMapping("/{missionId}")
    public MissionResponseDto getMission(@PathVariable Long missionId) {
        return missionService.getMission(missionId);
    }
}
