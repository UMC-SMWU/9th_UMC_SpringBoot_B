package com.example.chapter4.domain.mission.repository;

import com.example.chapter4.domain.mission.dto.CompletedMissionDto;
import com.example.chapter4.domain.mission.dto.InProgressMissionDto;
import com.example.chapter4.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    //진행중인 미션 조회
    @Query("SELECT new com.example.chapter4.domain.mission.dto.InProgressMissionDto(m.point, m.content, mm.id) " +
            "FROM MemberMission mm JOIN mm.mission m " +
            "WHERE mm.member.id = :memberId " +
            "  AND mm.isComplete = false " +
            "  AND mm.id < :lastCursorId " +
            "ORDER BY mm.id DESC")
    List<InProgressMissionDto> findInProgressMissions(
            @Param("memberId") Long memberId,
            @Param("lastCursorId") Long lastCursorId,
            Pageable pageable
    );

    //완료한 미션 조회
    @Query("SELECT new com.example.chapter4.domain.mission.dto.InProgressMissionDto(m.point, m.content, mm.id) " +
            "FROM MemberMission mm JOIN mm.mission m " +
            "WHERE mm.member.id = :memberId " +
            "  AND mm.isComplete = true " +
            "  AND mm.id < :lastCursorId " +
            "ORDER BY mm.id DESC")
    List<CompletedMissionDto> findCompletedMissions(
            @Param("memberId") Long memberId,
            @Param("lastCursorId") Long lastCursorId,
            Pageable pageable);


}
