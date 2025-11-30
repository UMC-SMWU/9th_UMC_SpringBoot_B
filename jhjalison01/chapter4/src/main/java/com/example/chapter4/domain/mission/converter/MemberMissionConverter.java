package com.example.chapter4.domain.mission.converter;

import com.example.chapter4.domain.member.entity.Member;
import com.example.chapter4.domain.mission.dto.req.MissionResDto;
import com.example.chapter4.domain.mission.dto.res.MemberMissionResponseDto;
import com.example.chapter4.domain.mission.entity.Mission;
import com.example.chapter4.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

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

    public static MissionResDto.MissionDetailDto toMissionDetailDto(MemberMission memberMission) {

        return MissionResDto.MissionDetailDto.builder()
                .missionId(memberMission.getMission().getId())
                .storeName(memberMission.getMission().getStore().getName()) // 지연 로딩 주의 (Repository에서 fetchJoin 필요)
                .content(memberMission.getMission().getContent())
                .point(memberMission.getMission().getPoint())
                .deadline(memberMission.getMission().getDeadline().toString())
                .build();
    }

    public static MissionResDto.MissionPreviewListDto toMissionPreviewListDto(Page<MemberMission> missionPage) {

        List<MissionResDto.MissionDetailDto> missionList = missionPage.stream()
                .map(MemberMissionConverter::toMissionDetailDto)
                .collect(Collectors.toList());

        return MissionResDto.MissionPreviewListDto.builder()
                .isLast(missionPage.isLast())
                .isFirst(missionPage.isFirst())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .listSize(missionList.size())
                .missionList(missionList)
                .build();
    }
}
