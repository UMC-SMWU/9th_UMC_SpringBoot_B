package com.example.chapter4.domain.review.service;

import com.example.chapter4.domain.review.converter.ReviewConverter;
import com.example.chapter4.domain.review.dto.ReviewListResponseDto;
import com.example.chapter4.domain.review.dto.ReviewRequestDto;
import com.example.chapter4.domain.review.dto.ReviewResponseDto;
import com.example.chapter4.domain.review.entity.Review;
import com.example.chapter4.domain.review.repository.ReviewRepository;
import com.example.chapter4.domain.store.entity.Store;
import com.example.chapter4.domain.store.repository.StoreRepository;
import com.example.chapter4.domain.user.entity.User;
import com.example.chapter4.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    public List<ReviewResponseDto> getMyReviews(String storeName, Integer rating) {
        Long userId = 1L; // 기존 컨벤션 유지(임시 하드코딩)

        List<Review> reviews = reviewRepository.findMyReviewsDynamic(userId, storeName, rating);

        return reviews.stream()
                .map(ReviewConverter::toReviewResponse)
                .toList();
    }

    @Transactional
    public ReviewResponseDto addReview(ReviewRequestDto request, Long userId) {
        User writer = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new IllegalArgumentException("Store not found"));

        Review newReview = ReviewConverter.toReview(request, writer, store);

        Review savedReview = reviewRepository.save(newReview);

        return ReviewConverter.toReviewResponse(savedReview);
    }

    public ReviewListResponseDto getMyReviewsPaged(
            Long userId, String storeName, Integer rating, int page0
    ) {
        Pageable pageable = PageRequest.of(
                page0, 10, Sort.by(Sort.Direction.DESC, "createdAt")
        );

        Page<Review> result = reviewRepository.findMyReviewsPaged(userId, storeName, rating, pageable);

        List<ReviewResponseDto> content = result.getContent().stream()
                .map(ReviewConverter::toReviewResponse)
                .toList();

        return ReviewListResponseDto.builder()
                .content(content)
                .page(result.getNumber() + 1) // 다시 1-based로 변환
                .size(result.getSize())
                .totalElements(result.getTotalElements())
                .totalPages(result.getTotalPages())
                .build();
    }
}