package com.example.chapter4.domain.mission.controller;

import com.example.chapter4.domain.mission.dto.MissionSearchCondition;
import com.example.chapter4.domain.mission.dto.req.MissionResDto;
import com.example.chapter4.domain.mission.service.query.MissionQueryService;
import com.example.chapter4.global.apiPayLoad.ApiResponse;
import com.example.chapter4.global.apiPayLoad.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController implements MissionControllerDocs{
    private final MissionQueryService missionQueryService;

    //특정 가게의 미션 목록 조회
    @GetMapping("/store/{storeId}")
    public ApiResponse<MissionResDto.MissionPreviewListDto> getMissionsByStore(
            @PathVariable Long storeId,
            @Valid @ModelAttribute MissionSearchCondition condition // 쿼리 스트링 -> 객체 바인딩 & 검증
    ) {
        MissionResDto.MissionPreviewListDto result = missionQueryService.getMissionsByStore(storeId, condition);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}
