package com.example.chapter4.domain.mission.service;

import com.example.chapter4.domain.mission.dto.ChallengeableMissionDto;
import com.example.chapter4.domain.mission.dto.CompletedMissionDto;
import com.example.chapter4.domain.mission.dto.InProgressMissionDto;
import com.example.chapter4.domain.mission.repository.MemberMissionRepository;
import com.example.chapter4.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {
    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;

    //진행중인 미션 가져오기
    public List<InProgressMissionDto> getInProgressMissions(Long memberId, Long lastCursorId) {
        // 첫 번째 페이지(0), 사이즈(3)
        Pageable pageable = PageRequest.of(0, 3);

        return memberMissionRepository.findInProgressMissions(memberId, lastCursorId, pageable);
    }

    //완료한 미션 가져오기
    public List<CompletedMissionDto> getCompletedMissions(Long memberId, Long lastCursorId) {
        // 첫 번째 페이지(0), 사이즈(3)
        Pageable pageable = PageRequest.of(0, 3);

        return memberMissionRepository.findCompletedMissions(memberId, lastCursorId, pageable);
    }

    //도전 가능한 미션 가져오기
    public List<ChallengeableMissionDto> getChallengeableMissions(Long memberId, String locationName, Long lastCursorId) {
        // Pageable 객체를 생성하여 LIMIT 3 효과를 줍니다.
        Pageable pageable = PageRequest.of(0, 3);

        return missionRepository.findChallengeableMissions(memberId, locationName, lastCursorId, pageable);
    }
}
