package com.example.chapter4.global.exception;

import com.example.chapter4.global.apiPayload.code.GeneralErrorCode;

public class PageValidationException extends GeneralException {
    public PageValidationException() {
        super(GeneralErrorCode.PAGE_VALIDATION_ERROR);
        // GeneralErrorCode에 아래 enum 추가 필요
    }
}