package com.example.chapter4.domain.review.dto;

import com.example.chapter4.domain.review.entity.Review;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class ReviewResponseDto {
    private Long id;
    private Long storeId;
    private String storeName;
    private Long writerId;
    private String writerNickname;
    private Float rating;
    private String content;
    private LocalDateTime createdAt;

    public ReviewResponseDto(Review review) {
        this.id = review.getId();
        this.storeId = review.getStore().getId();
        this.storeName = review.getStore().getName();
        this.writerId = review.getWriter().getId();
        this.writerNickname = review.getWriter().getUsername();
        this.rating = review.getRating();
        this.content = review.getContent();
        this.createdAt = review.getCreatedAt();
    }
}
