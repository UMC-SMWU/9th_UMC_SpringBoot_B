package com.example.chapter4.domain.review.service;

import com.example.chapter4.domain.review.dto.ReviewResponseDto;
import com.example.chapter4.domain.review.entity.Review;
import com.example.chapter4.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<ReviewResponseDto> getMyReviews(String storeName, Integer rating) {
        Long currentUserId = 1L; // 실제 서비스에선 인증정보로 대체!
        List<Review> reviews = reviewRepository.findMyReviewsDynamic(currentUserId, storeName, rating);

        return reviews.stream()
                .map(ReviewResponseDto::new)
                .collect(Collectors.toList());
    }
}
