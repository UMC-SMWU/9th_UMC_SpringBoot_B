package com.example.chapter4.domain.mission.repository;

import com.example.chapter4.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}