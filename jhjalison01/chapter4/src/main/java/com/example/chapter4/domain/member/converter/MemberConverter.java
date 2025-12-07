package com.example.chapter4.domain.member.converter;

import com.example.chapter4.domain.member.dto.req.MemberReqDto;
import com.example.chapter4.domain.member.dto.res.MemberResDto;
import com.example.chapter4.domain.member.entity.Member;
import com.example.chapter4.domain.member.enums.Status;
import com.example.chapter4.global.auth.enums.Role;

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
            MemberReqDto.JoinDto dto,
            String password,
            Role role
    ){
        return Member.builder()
                .name(dto.name())
                .email(dto.email())
                .password(password)
                .role(role)
                .birth(dto.birth())
                .address(dto.address())
                //.detailAddress(dto.specAddress())
                .gender(dto.gender())
                .phoneNum(dto.phoneNum())
                .status(Status.IN_USE)
                .build();
    }

    // Entity -> DTO
    public static MemberResDto.LoginDTO toLoginDTO(Member member, String accessToken) {
        return MemberResDto.LoginDTO.builder()
                .memberId(member.getId())
                .accessToken(accessToken)
                .build();
    }

}
