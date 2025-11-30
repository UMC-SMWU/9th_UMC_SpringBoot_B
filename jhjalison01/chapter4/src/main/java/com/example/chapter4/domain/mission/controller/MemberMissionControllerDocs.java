package com.example.chapter4.domain.mission.controller;

import com.example.chapter4.domain.mission.dto.MissionSearchCondition;
import com.example.chapter4.domain.mission.dto.req.MissionResDto;

import com.example.chapter4.global.apiPayLoad.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

public interface MemberMissionControllerDocs {


    @Operation(summary = "사용자가 진행 중인 미션 목록 조회", description = "회원이 현재 도전 중인(완료하지 않은) 미션 목록을 조회합니다. 페이징을 포함합니다.")
    @Parameters({
            @Parameter(name = "memberId", description = "회원의 아이디"),
            @Parameter(name = "page", description = "페이지 번호 (1부터 시작)")
    })
    ApiResponse<MissionResDto.MissionPreviewListDto> getChallengingMissions(
            @PathVariable Long memberId,
            @Valid @ModelAttribute MissionSearchCondition condition // 쿼리 스트링 바인딩 & 검증
    );


}
