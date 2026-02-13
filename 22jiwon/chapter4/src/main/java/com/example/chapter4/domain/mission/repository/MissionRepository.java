package com.example.chapter4.domain.mission.repository;

import com.example.chapter4.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 특정 지역의 미션 조회 (기존)
    @Query("select m from Mission m where m.store.region.id = :regionId")
    Page<Mission> findByRegionId(@Param("regionId") Long regionId, Pageable pageable);

    // 특정 가게의 미션 조회 (페이징)
    @Query("select m from Mission m where m.store.id = :storeId")
    Page<Mission> findByStoreId(@Param("storeId") Long storeId, Pageable pageable);
}
