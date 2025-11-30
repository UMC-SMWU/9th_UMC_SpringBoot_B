package com.example.chapter4.domain.mission.service.command;

import com.example.chapter4.domain.mission.dto.req.MemberMissionRequestDto;
import com.example.chapter4.domain.mission.entity.mapping.MemberMission;

public interface MemberMissionCommandService {
    MemberMission createMemberMission(MemberMissionRequestDto.CreateDto request, Long memberId);
}
