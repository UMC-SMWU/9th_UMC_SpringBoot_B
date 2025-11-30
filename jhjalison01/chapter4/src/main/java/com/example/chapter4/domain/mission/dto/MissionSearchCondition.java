package com.example.chapter4.domain.mission.dto;

import com.example.chapter4.global.annotation.CheckPage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MissionSearchCondition {
    @Schema(description = "페이지 번호 (1 이상)", example = "1")
    @CheckPage // 커스텀 어노테이션 적용 (0 이하 검증)
    private Integer page;
}
