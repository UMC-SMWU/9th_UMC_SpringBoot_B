package com.example.chapter4.domain.review.code;

import com.example.chapter4.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW404_1", "해당 리뷰를 찾을 수 없습니다."),
    INVALID_PERMISSION(HttpStatus.FORBIDDEN, "REVIEW403_1", "리뷰를 수정/삭제할 권한이 없습니다."),
    DUPLICATE_REVIEW(HttpStatus.BAD_REQUEST, "REVIEW400_1", "이미 리뷰를 작성하였습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
