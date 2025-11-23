package com.example.chapter4.domain.review.repository;

import com.example.chapter4.domain.review.entity.Review;
import com.example.chapter4.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryDsl{
    Page<Review> findAllByStore(Store store, PageRequest pageRequest);
}
