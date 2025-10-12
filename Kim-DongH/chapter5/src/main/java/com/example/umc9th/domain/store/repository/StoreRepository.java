package com.example.umc9th.domain.store.repository;

import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.enums.Address;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Page<Store> findByAddress(Address address, Pageable pageable);
}

