package com.example.chapter4.domain.review.service.query;

import com.example.chapter4.domain.review.dto.req.ReviewRequestDto;
import com.example.chapter4.domain.review.entity.Review;

import java.util.List;

public interface ReviewQueryService {
    List<Review> searchReview(String query, String type);
}
