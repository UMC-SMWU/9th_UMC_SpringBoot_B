package com.example.chapter4.domain.region.repository;

import com.example.chapter4.domain.region.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Long> {
    boolean existsByName(String name);
}