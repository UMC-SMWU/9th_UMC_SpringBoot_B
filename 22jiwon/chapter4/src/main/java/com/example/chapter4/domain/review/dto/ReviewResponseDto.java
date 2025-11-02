package com.example.chapter4.domain.review.dto;

import com.example.chapter4.domain.review.entity.Review;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class ReviewResponseDto {
    private Long id;
    private String storeName;
    private String writerNickname;
    private Float rating;
    private String content;
    private LocalDateTime createdAt;

    // 엔티티를 DTO로 변환하는 생성자
    public ReviewResponseDto(Review review) {
        this.id = review.getId();
        this.storeName = review.getStore().getName();
        this.writerNickname = review.getWriter().getUsername(); // or getNickname()
        this.rating = review.getRating();
        this.content = review.getContent();
        this.createdAt = review.getCreatedAt();
    }
}