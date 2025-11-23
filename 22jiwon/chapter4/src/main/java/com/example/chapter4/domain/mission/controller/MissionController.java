package com.example.chapter4.domain.mission.controller;

import com.example.chapter4.domain.mission.dto.MissionListResponseDto;
import com.example.chapter4.domain.mission.dto.MissionResponseDto;
import com.example.chapter4.domain.mission.dto.UserMissionChallengeRequestDto;
import com.example.chapter4.domain.mission.dto.UserMissionChallengeResponseDto;
import com.example.chapter4.domain.mission.dto.UserMissionListResponseDto;
import com.example.chapter4.domain.mission.service.MissionService;
import com.example.chapter4.global.annotation.CheckPage;
import com.example.chapter4.global.annotation.ExistStore;
import com.example.chapter4.global.apiPayload.ApiResponse;
import com.example.chapter4.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @GetMapping
    public ApiResponse<MissionListResponseDto> getMissions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
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
        request.setMissionId(missionId);
        Long userId = 1L;
        UserMissionChallengeResponseDto response = missionService.challengeMission(request, userId);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, response);
    }

    //  특정 가게의 미션 목록 조회
    @Operation(
            summary = "특정 가게의 미션 목록 조회",
            description = "storeId에 해당하는 미션을 10개 단위로 페이징 조회합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "page 오류"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "가게 없음")
    })
    @GetMapping("/stores/{storeId}")
    public ApiResponse<MissionListResponseDto> getMissionsByStore(
            @PathVariable @ExistStore Long storeId,
            @CheckPage Integer page
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                missionService.getMissionsByStore(storeId, page)
        );
    }

    // 내가 진행중인 미션 목록 조회
    @Operation(
            summary = "내가 진행중인 미션 목록 조회",
            description = "로그인한 사용자가 진행중(IN_PROGRESS)인 미션을 10개 단위로 페이징 조회합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "page 오류")
    })
    @GetMapping("/me/in-progress")
    public ApiResponse<UserMissionListResponseDto.UserMissionPreviewListDto> getMyInProgressMissions(
            @CheckPage Integer page
    ) {
        Long userId = 1L; // 기존 컨벤션 유지(임시 하드코딩)
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                missionService.getMyInProgressMissions(userId, page)
        );
    }

    // 진행중 미션 완료 처리 + 변경된 상태 반환
    @Operation(
            summary = "진행중 미션 완료 처리",
            description = "내가 진행중(IN_PROGRESS)인 미션을 COMPLETED로 변경하고, 변경된 상태를 반환합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "진행중 미션이 아님"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "도전 기록 없음/미션 없음")
    })
    @PatchMapping("/{missionId}/complete")
    public ApiResponse<UserMissionChallengeResponseDto> completeMission(
            @PathVariable Long missionId
    ) {
        Long userId = 1L; // 기존 컨벤션 유지(임시 하드코딩)

        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                missionService.completeMyMission(missionId, userId)
        );
    }
}
