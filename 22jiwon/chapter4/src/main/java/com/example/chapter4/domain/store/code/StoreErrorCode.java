package com.example.chapter4.domain.store.code;

import com.example.chapter4.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StoreErrorCode implements BaseErrorCode {
    NOT_FOUND(HttpStatus.NOT_FOUND, "STORE404_1", "해당 가게가 존재하지 않습니다.");
    private final HttpStatus status;
    private final String code;
    private final String message;
}
