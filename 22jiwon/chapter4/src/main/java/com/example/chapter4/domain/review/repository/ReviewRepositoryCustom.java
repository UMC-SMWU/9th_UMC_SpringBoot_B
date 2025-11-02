package com.example.chapter4.domain.review.repository;

import com.example.chapter4.domain.review.entity.Review;
import java.util.List;

public interface ReviewRepositoryCustom {

    List<Review> findMyReviewsDynamic(Long writerId, String storeName, Integer rating);
}