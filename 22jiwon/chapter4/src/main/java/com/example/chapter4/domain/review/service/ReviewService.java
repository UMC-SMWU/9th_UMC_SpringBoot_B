package com.example.chapter4.domain.review.service;

import com.example.chapter4.domain.review.dto.ReviewRequestDto;
import com.example.chapter4.domain.review.dto.ReviewResponseDto;
import com.example.chapter4.domain.review.entity.Review;
import com.example.chapter4.domain.review.repository.ReviewRepository;
import com.example.chapter4.domain.store.entity.Store;
import com.example.chapter4.domain.store.repository.StoreRepository;
import com.example.chapter4.domain.user.entity.User;
import com.example.chapter4.domain.user.repository.UserRepository;
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
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    public List<ReviewResponseDto> getMyReviews(String storeName, Integer rating) {
        Long currentUserId = 1L;
        List<Review> reviews = reviewRepository.findMyReviewsDynamic(currentUserId, storeName, rating);

        return reviews.stream()
                .map(ReviewResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public ReviewResponseDto addReview(ReviewRequestDto dto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저 없음"));
        Store store = storeRepository.findById(dto.getStoreId())
                .orElseThrow(() -> new IllegalArgumentException("가게 없음"));

        Review review = Review.builder()
                .rating(dto.getRating())
                .content(dto.getContent())
                .writer(user)
                .store(store)
                .build();

        reviewRepository.save(review);
        return new ReviewResponseDto(review);
    }
}
