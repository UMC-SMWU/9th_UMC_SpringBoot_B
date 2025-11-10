package com.example.chapter4.domain.mission.repository;

import com.example.chapter4.domain.mission.entity.UserMissionCompletion;
import com.example.chapter4.domain.mission.entity.Mission;
import com.example.chapter4.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserMissionCompletionRepository extends JpaRepository<UserMissionCompletion, Long> {

    Optional<UserMissionCompletion> findByUserAndMission(User user, Mission mission);

    // (기존 코드)
    // Page<UserMissionCompletion> findByUserId(Long userId, Pageable pageable);
}