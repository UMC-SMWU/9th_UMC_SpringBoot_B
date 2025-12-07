package com.example.chapter4.domain.member.exception;

import com.example.chapter4.global.apiPayLoad.code.BaseErrorCode;
import com.example.chapter4.global.apiPayLoad.exception.GeneralException;

public class FoodException extends GeneralException {
    public FoodException(BaseErrorCode code){
        super(code);
    }
}
