package com.example.chapter4.domain.mission.code;

import com.example.chapter4.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404_1", "존재하지 않는 미션입니다."),
    ALREADY_CHALLENGED(HttpStatus.CONFLICT, "MISSION409_1", "이미 도전 중인 미션입니다."),
    INVALID_STATUS(HttpStatus.BAD_REQUEST, "MISSION400_1", "미션 상태가 올바르지 않습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
