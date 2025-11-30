package com.example.chapter4.domain.mission.repository;

import com.example.chapter4.domain.mission.entity.Mission;
import com.example.chapter4.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberMissionQueryDsl {
    // 진행 중인 미션 목록 조회 (페이징 포함)
    Page<MemberMission> findChallengingMissions(Long memberId, Pageable pageable);

}
