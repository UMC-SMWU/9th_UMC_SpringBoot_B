package com.example.chapter4.domain.mission.repository;

import com.example.chapter4.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MissionQueryDsl {
    Page<Mission> findMissionsByStoreId(Long storeId, Pageable pageable);
}
