package com.example.chapter4.domain.mission.repository;

import com.example.chapter4.domain.mission.dto.ChallengeableMissionDto;
import com.example.chapter4.domain.mission.entity.Mission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission,Long> {

    //도전 가능한 미션 조회 (페이징)
    @Query("SELECT new com.example.chapter4.domain.mission.dto.ChallengeableMissionDto(m.point, m.deadline, m.content, s.type, s.name) " +
            "FROM Mission m " +
            "JOIN m.store s " +
            "JOIN s.location l " +
            "LEFT JOIN MemberMission mm ON m = mm.mission AND mm.member.id = :memberId " +
            "WHERE mm.id IS NULL " + // LEFT JOIN에 아직 도전하지 않은 미션만 필터링
            "  AND l.name = :locationName " +
            "  AND m.id < :lastCursorId " +
            "ORDER BY m.id DESC")
    List<ChallengeableMissionDto> findChallengeableMissions(
            @Param("memberId") Long memberId,
            @Param("locationName") String locationName,
            @Param("lastCursorId") Long lastCursorId,
            Pageable pageable);
}
