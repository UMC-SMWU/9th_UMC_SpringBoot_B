package com.example.chapter4.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode {

    // 일반적인 성공 케이스 (200 OK)
    OK(HttpStatus.OK, "COMMON200", "성공적으로 요청을 처리했습니다."),

    // 201 Created
    CREATED(HttpStatus.CREATED, "COMMON201", "성공적으로 생성되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}