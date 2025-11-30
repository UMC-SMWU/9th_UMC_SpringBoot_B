package com.example.chapter4.domain.member.converter;

import com.example.chapter4.domain.member.dto.req.MemberReqDto;
import com.example.chapter4.domain.member.dto.res.MemberResDto;
import com.example.chapter4.domain.member.entity.Member;

public class MemberConverter {
    // Entity -> DTO
    public static MemberResDto.JoinDto toJoinDto(
            Member member
    ){
        return MemberResDto.JoinDto.builder()
                .memberId(member.getId())
                .createAt(member.getCreatedAt())
                .build();
    }

    // DTO -> Entity
    public static Member toMember(
            MemberReqDto.JoinDto dto
    ){
        return Member.builder()
                .name(dto.name())
                .birth(dto.birth())
                .address(dto.address())
                //.detailAddress(dto.specAddress())
                .gender(dto.gender())
                .build();
    }
}
