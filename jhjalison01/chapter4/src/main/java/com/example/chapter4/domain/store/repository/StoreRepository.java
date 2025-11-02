package com.example.chapter4.domain.store.repository;

import com.example.chapter4.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
