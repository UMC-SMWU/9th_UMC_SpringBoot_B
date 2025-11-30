package com.example.chapter4.domain.mission.controller;

import com.example.chapter4.domain.mission.converter.MemberMissionConverter;
import com.example.chapter4.domain.mission.dto.MissionSearchCondition;
import com.example.chapter4.domain.mission.dto.req.MemberMissionRequestDto;
import com.example.chapter4.domain.mission.dto.req.MissionResDto;
import com.example.chapter4.domain.mission.dto.res.MemberMissionResponseDto;
import com.example.chapter4.domain.mission.entity.mapping.MemberMission;
import com.example.chapter4.domain.mission.service.command.MemberMissionCommandService;
import com.example.chapter4.domain.mission.service.query.MemberMissionQueryService;
import com.example.chapter4.global.apiPayLoad.ApiResponse;
import com.example.chapter4.global.apiPayLoad.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member-missions")
public class MemberMissionController implements MemberMissionControllerDocs{
    private final MemberMissionCommandService memberMissionCommandService;
    private final MemberMissionQueryService memberMissionQueryService;

    //미션 도전하기
    @PostMapping
    public ApiResponse<MemberMissionResponseDto.CreateResultDto> createMemberMission(
            @RequestBody MemberMissionRequestDto.CreateDto request
    ) {
        Long memberId = 1L;

        MemberMission memberMission = memberMissionCommandService.createMemberMission(request, memberId);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK,MemberMissionConverter.toCreateResultDto(memberMission));
    }

    //사용자가 진행중인 미션 목록 조회
    @GetMapping("/{memberId}/missions/challenging")
    public ApiResponse<MissionResDto.MissionPreviewListDto> getChallengingMissions(
            @PathVariable Long memberId,
            @Valid @ModelAttribute MissionSearchCondition condition // 쿼리 스트링 바인딩 & 검증
    ) {
        MissionResDto.MissionPreviewListDto result = memberMissionQueryService.getChallengingMissions(memberId, condition);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}
