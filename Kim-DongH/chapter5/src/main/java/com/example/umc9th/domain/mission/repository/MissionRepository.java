package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.dto.MyMissionItem;
import com.example.umc9th.domain.mission.dto.*;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.store.dto.HomeMissionItem;
import com.example.umc9th.domain.store.enums.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("""
    select new com.example.umc9th.domain.mission.dto.MyMissionItem(
        mm.mission.id, mm.mission.title, s.name, cast(mm.status as string), mm.updatedAt
    )
    from MemberMission mm
    join mm.mission m
    join m.store s
    where mm.member.id = :memberId and cast(mm.status as string) = :status
    order by mm.updatedAt desc
    """)
    Page<MyMissionItem> findMyMissions(@Param("memberId") Long memberId,
                                       @Param("status") String status,
                                       Pageable pageable);
}

