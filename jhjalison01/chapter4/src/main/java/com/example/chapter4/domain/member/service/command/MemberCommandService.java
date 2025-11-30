package com.example.chapter4.domain.member.service.command;

import com.example.chapter4.domain.member.dto.req.MemberReqDto;
import com.example.chapter4.domain.member.dto.res.MemberResDto;

public interface MemberCommandService {
    MemberResDto.JoinDto signup(MemberReqDto.JoinDto dto);
}
