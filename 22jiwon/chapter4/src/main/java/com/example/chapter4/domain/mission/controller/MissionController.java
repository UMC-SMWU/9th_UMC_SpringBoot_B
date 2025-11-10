package com.example.chapter4.domain.mission.controller;

import com.example.chapter4.domain.mission.dto.MissionListResponseDto;
import com.example.chapter4.domain.mission.dto.MissionResponseDto;
import com.example.chapter4.domain.mission.dto.UserMissionChallengeRequestDto;
import com.example.chapter4.domain.mission.dto.UserMissionChallengeResponseDto;
import com.example.chapter4.domain.mission.service.MissionService;
import jakarta.validation.Valid;
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

    @PostMapping("/{missionId}/challenge")
    public ApiResponse<UserMissionChallengeResponseDto> challengeMission(
            @PathVariable Long missionId,
            @RequestBody @Valid UserMissionChallengeRequestDto request
    ) {
        // request에 missionId가 없다면 아래 한 줄 추가
        request.setMissionId(missionId);
        Long userId = 1L; // 인증연동 전 임시 하드코딩
        UserMissionChallengeResponseDto response = missionService.challengeMission(request, userId);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, response);
    }
}
