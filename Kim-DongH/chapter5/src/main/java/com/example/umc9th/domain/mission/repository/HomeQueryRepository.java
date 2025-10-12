package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.store.dto.HomeMissionItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface HomeQueryRepository extends JpaRepository<com.example.umc9th.domain.mission.entity.Mission, Long> {

    @Query("""
    select new com.example.umc9th.domain.store.dto.HomeMissionItem(
        m.id, m.title, s.name, s.region.name
    )
    from Mission m
    join m.store s
    where s.region.id = :regionId
      and m.status = 'OPEN'
    order by m.createdAt desc
    """)
    Page<HomeMissionItem> findOpenMissionsByRegion(@Param("regionId") Long regionId, Pageable pageable);
}


