package com.example.chapter4.domain.store.exception;

import com.example.chapter4.global.apiPayLoad.code.BaseErrorCode;
import com.example.chapter4.global.apiPayLoad.exception.GeneralException;

public class StoreException extends GeneralException {
    public StoreException(BaseErrorCode code) {
        super(code);
    }
}
