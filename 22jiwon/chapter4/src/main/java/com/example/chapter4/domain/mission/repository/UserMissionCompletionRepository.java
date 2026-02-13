package com.example.chapter4.domain.mission.repository;

import com.example.chapter4.domain.mission.entity.Mission;
import com.example.chapter4.domain.mission.entity.UserMissionCompletion;
import com.example.chapter4.domain.mission.entity.UserMissionStatus;
import com.example.chapter4.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserMissionCompletionRepository extends JpaRepository<UserMissionCompletion, Long> {

    // 유저-미션 단건 조회 (도전 중복 체크용)
    Optional<UserMissionCompletion> findByUserAndMission(User user, Mission mission);

    // 내가 진행중(IN_PROGRESS)인 미션 목록 페이징 조회
    Page<UserMissionCompletion> findByUserIdAndStatus(
            Long userId,
            UserMissionStatus status,
            Pageable pageable
    );

    // (기존 코드)
    // Page<UserMissionCompletion> findByUserId(Long userId, Pageable pageable);
}