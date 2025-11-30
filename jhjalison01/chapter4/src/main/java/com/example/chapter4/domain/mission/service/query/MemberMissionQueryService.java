package com.example.chapter4.domain.mission.service.query;

import com.example.chapter4.domain.mission.dto.MissionSearchCondition;
import com.example.chapter4.domain.mission.dto.req.MissionResDto;

public interface MemberMissionQueryService {
    MissionResDto.MissionPreviewListDto getChallengingMissions(Long memberId, MissionSearchCondition condition);
}
