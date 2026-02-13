package com.example.chapter4.domain.mission.service;

import com.example.chapter4.domain.mission.converter.MissionConverter;
import com.example.chapter4.domain.mission.dto.MissionListResponseDto;
import com.example.chapter4.domain.mission.dto.MissionResponseDto;
import com.example.chapter4.domain.mission.dto.UserMissionChallengeRequestDto;
import com.example.chapter4.domain.mission.dto.UserMissionChallengeResponseDto;
import com.example.chapter4.domain.mission.converter.UserMissionConverter;
import com.example.chapter4.domain.mission.dto.UserMissionListResponseDto;
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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final UserMissionCompletionRepository userMissionCompletionRepository;
    private final UserRepository userRepository;

    // 기존 전체 미션 조회 (컨벤션 통일: Converter + Builder)
    public MissionListResponseDto getMissions(int page, int size) {
        Page<Mission> missions = missionRepository.findAll(PageRequest.of(page, size));
        return MissionConverter.toMissionListResponseDto(missions);
    }

    public MissionResponseDto getMission(Long missionId) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new IllegalArgumentException("미션 없음"));
        return MissionConverter.toMissionResponseDto(mission);
    }

    //  특정 가게의 미션 목록 조회 (페이징 10개)
    public MissionListResponseDto getMissionsByStore(Long storeId, int page0) {

        PageRequest pageable = PageRequest.of(
                page0,
                10,
                Sort.by(Sort.Direction.DESC, "createdAt")
        );

        Page<Mission> missions = missionRepository.findByStoreId(storeId, pageable);

        return MissionConverter.toMissionListResponseDto(missions);
    }

    public UserMissionListResponseDto.UserMissionPreviewListDto getMyInProgressMissions(
            Long userId, int page0
    ) {
        PageRequest pageable = PageRequest.of(
                page0,
                10,
                Sort.by(Sort.Direction.DESC, "challengedAt")
        );

        Page<UserMissionCompletion> result =
                userMissionCompletionRepository.findByUserIdAndStatus(
                        userId, UserMissionStatus.IN_PROGRESS, pageable
                );

        return UserMissionConverter.toUserMissionPreviewListDto(result);
    }

    // 미션 도전 기능
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

        return UserMissionChallengeResponseDto.from(challenge);
    }

    // 진행중 미션 > 완료 처리 + 변경된 상태 반환
    @Transactional
    public UserMissionChallengeResponseDto completeMyMission(Long missionId, Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저 없음"));

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new IllegalArgumentException("미션 없음"));

        UserMissionCompletion umc = userMissionCompletionRepository.findByUserAndMission(user, mission)
                .orElseThrow(() -> new IllegalArgumentException("도전 기록 없음"));

        if (umc.getStatus() != UserMissionStatus.IN_PROGRESS) {
            throw new IllegalStateException("진행중인 미션이 아닙니다.");
        }

        // 상태 변경 (IN_PROGRESS -> COMPLETED)
        umc.completeMission();

        // 변경된 엔티티 저장 (dirty checking으로도 반영되지만 명시 저장)
        userMissionCompletionRepository.save(umc);

        // 변경된 상태 그대로 반환
        return UserMissionChallengeResponseDto.from(umc);
    }
}