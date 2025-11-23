package com.example.chapter4.domain.mission.exception.code;

import com.example.chapter4.global.apiPayLoad.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {
    NOT_FOUND(HttpStatus.NOT_FOUND,
            "Mission404_1",
            "해당 음식을 찾지 못했습니다."),
    ALREADY_CHALLENGED(HttpStatus.CONFLICT, "MISSION409_1", "이미 도전 중인 미션입니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
