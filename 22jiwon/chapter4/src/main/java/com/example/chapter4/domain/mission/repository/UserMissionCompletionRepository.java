package com.example.chapter4.domain.mission.repository;

import com.example.chapter4.domain.mission.entity.UserMissionCompletion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMissionCompletionRepository extends JpaRepository<UserMissionCompletion, Long> {

    // 특정 유저의 미션 완료 현황 (페이징)
    Page<UserMissionCompletion> findByUserId(Long userId, Pageable pageable);
}