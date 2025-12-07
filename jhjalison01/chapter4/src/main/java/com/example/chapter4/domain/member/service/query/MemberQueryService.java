package com.example.chapter4.domain.member.service.query;

import com.example.chapter4.domain.member.dto.req.MemberReqDto;
import com.example.chapter4.domain.member.dto.res.MemberResDto;
import jakarta.validation.Valid;

public interface MemberQueryService {
    MemberResDto.LoginDTO login(MemberReqDto.@Valid LoginDTO dto);
}
