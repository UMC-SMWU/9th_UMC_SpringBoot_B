package com.example.chapter4.domain.review.repository;

import com.example.chapter4.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {

    // 내가 쓴 리뷰 (마이페이지)
    List<Review> findByWriterId(Long userId);

    // 특정 가게의 리뷰들
    List<Review> findByStoreId(Long storeId);

    // @Query 예시 (조건 커스터마이징 가능)
    @Query("select r from Review r where r.writer.id = :userId and r.store.id = :storeId")
    List<Review> findByUserAndStore(@Param("userId") Long userId, @Param("storeId") Long storeId);
}