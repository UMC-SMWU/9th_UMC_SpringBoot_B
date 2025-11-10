package com.example.chapter4.domain.mission.service;

import com.example.chapter4.domain.mission.dto.MissionListResponseDto;
import com.example.chapter4.domain.mission.dto.MissionResponseDto;
import com.example.chapter4.domain.mission.dto.UserMissionChallengeRequestDto;
import com.example.chapter4.domain.mission.dto.UserMissionChallengeResponseDto;
import com.example.chapter4.domain.mission.entity.Mission;
import com.example.chapter4.domain.mission.entity.UserMissionCompletion;
import com.example.chapter4.domain.mission.entity.UserMissionStatus;
import com.example.chapter4.domain.mission.repository.MissionRepository;
import com.example.chapter4.domain.mission.repository.UserMissionCompletionRepository;
import com.example.chapter4.domain.user.entity.User;
import com.example.chapter4.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final UserMissionCompletionRepository userMissionCompletionRepository;
    private final UserRepository userRepository;

    public MissionListResponseDto getMissions(int page, int size) {
        Page<Mission> missions = missionRepository.findAll(PageRequest.of(page, size));
        List<MissionResponseDto> content = missions.stream()
                .map(MissionResponseDto::new)
                .collect(Collectors.toList());
        return new MissionListResponseDto(content, missions.getNumber(), missions.getSize(), missions.getTotalElements(), missions.getTotalPages());
    }

    public MissionResponseDto getMission(Long missionId) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new IllegalArgumentException("미션 없음"));
        return new MissionResponseDto(mission);
    }

    //  미션 도전 기능 추가
    @Transactional
    public UserMissionChallengeResponseDto challengeMission(UserMissionChallengeRequestDto request, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저 없음"));
        Mission mission = missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new IllegalArgumentException("미션 없음"));

        userMissionCompletionRepository.findByUserAndMission(user, mission).ifPresent(existing -> {
            throw new IllegalStateException("이미 도전 중인 미션입니다.");
        });
        UserMissionCompletion challenge = UserMissionCompletion.builder()
                .user(user)
                .mission(mission)
                .status(UserMissionStatus.IN_PROGRESS)
                .challengedAt(LocalDateTime.now())
                .completedAt(null)
                .build();

        userMissionCompletionRepository.save(challenge);
        return new UserMissionChallengeResponseDto(challenge);
    }
}
