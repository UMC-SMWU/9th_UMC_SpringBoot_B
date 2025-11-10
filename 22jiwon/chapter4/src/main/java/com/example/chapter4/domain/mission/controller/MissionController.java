package com.example.chapter4.domain.mission.controller;

import com.example.chapter4.domain.mission.dto.MissionListResponseDto;
import com.example.chapter4.domain.mission.dto.MissionResponseDto;
import com.example.chapter4.domain.mission.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.chapter4.global.apiPayload.ApiResponse;
import com.example.chapter4.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequestMapping("/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @GetMapping
    public ApiResponse<MissionListResponseDto> getMissions(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "20") int size) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missionService.getMissions(page, size));
    }

    @GetMapping("/{missionId}")
    public ApiResponse<MissionResponseDto> getMission(@PathVariable Long missionId) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missionService.getMission(missionId));
    }
}
