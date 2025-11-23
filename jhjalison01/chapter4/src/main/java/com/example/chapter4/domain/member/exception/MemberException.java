package com.example.chapter4.domain.member.exception;

import com.example.chapter4.global.apiPayLoad.code.BaseErrorCode;
import com.example.chapter4.global.apiPayLoad.exception.GeneralException;

public class MemberException extends GeneralException {
    public MemberException(BaseErrorCode code){
        super(code);
    }
}
