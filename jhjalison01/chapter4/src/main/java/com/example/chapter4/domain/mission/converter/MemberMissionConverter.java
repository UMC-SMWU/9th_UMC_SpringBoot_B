package com.example.chapter4.domain.mission.converter;

import com.example.chapter4.domain.member.entity.Member;
import com.example.chapter4.domain.mission.dto.res.MemberMissionResponseDto;
import com.example.chapter4.domain.mission.entity.Mission;
import com.example.chapter4.domain.mission.entity.mapping.MemberMission;

public class MemberMissionConverter {
    public static MemberMission toMemberMission(Member member, Mission mission) {
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .isComplete(false)
                .build();
    }

    public static MemberMissionResponseDto.CreateResultDto toCreateResultDto(MemberMission memberMission) {
        return MemberMissionResponseDto.CreateResultDto.builder()
                .memberMissionId(memberMission.getId())
                .createdAt(memberMission.getCreatedAt())
                .build();
    }
}
