package com.example.chapter4.domain.review.repository;

import com.example.chapter4.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewRepositoryCustom {

    List<Review> findMyReviewsDynamic(Long writerId, String storeName, Integer rating);

    Page<Review> findMyReviewsPaged(Long writerId, String storeName, Integer rating, Pageable pageable);
}
