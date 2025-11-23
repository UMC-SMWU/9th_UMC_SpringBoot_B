package com.example.chapter4.domain.mission.controller;

import com.example.chapter4.domain.mission.converter.MemberMissionConverter;
import com.example.chapter4.domain.mission.dto.req.MemberMissionRequestDto;
import com.example.chapter4.domain.mission.dto.res.MemberMissionResponseDto;
import com.example.chapter4.domain.mission.entity.mapping.MemberMission;
import com.example.chapter4.domain.mission.service.command.MemberMissionCommandService;
import com.example.chapter4.global.apiPayLoad.ApiResponse;
import com.example.chapter4.global.apiPayLoad.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member-missions")
public class MemberMissionController {
    private final MemberMissionCommandService memberMissionCommandService;

    //미션 도전하기
    @PostMapping
    public ApiResponse<MemberMissionResponseDto.CreateResultDto> createMemberMission(
            @RequestBody MemberMissionRequestDto.CreateDto request
    ) {
        Long memberId = 1L;

        MemberMission memberMission = memberMissionCommandService.createMemberMission(request, memberId);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK,MemberMissionConverter.toCreateResultDto(memberMission));
    }
}
