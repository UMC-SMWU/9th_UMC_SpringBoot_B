package com.example.chapter4.domain.mission.service.query;

import com.example.chapter4.domain.mission.dto.ChallengeableMissionDto;
import com.example.chapter4.domain.mission.dto.CompletedMissionDto;
import com.example.chapter4.domain.mission.dto.InProgressMissionDto;

import java.util.List;

public interface MissionQueryService {
    List<InProgressMissionDto> getInProgressMissions(Long memberId, Long lastCursorId);

    List<CompletedMissionDto> getCompletedMissions(Long memberId, Long lastCursorId);

    List<ChallengeableMissionDto> getChallengeableMissions(Long memberId, String locationName, Long lastCursorId);
}
