package com.example.umc9th.domain.review.entity;

import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "review_photo",
        indexes = @Index(name="idx_review_photo_review", columnList="review_id"))

public class ReviewPhoto extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewPhotoId;

    @Column(nullable = false, length = 255)
    private String photoUrl;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "review_id", nullable = false)
    private Review review;

}
