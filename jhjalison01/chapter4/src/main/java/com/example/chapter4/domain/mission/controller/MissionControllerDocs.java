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

public interface MissionControllerDocs {
    @Operation(summary = "특정 가게의 미션 목록 조회", description = "특정 가게의 미션 목록을 조회합니다.")
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디"),
            @Parameter(name = "page", description = "페이지 번호 (1부터 시작)")
    })
    ApiResponse<MissionResDto.MissionPreviewListDto> getMissionsByStore(
            @PathVariable Long storeId,
            @Valid @ModelAttribute MissionSearchCondition condition // 쿼리 스트링 -> 객체 바인딩 & 검증
    );
}
