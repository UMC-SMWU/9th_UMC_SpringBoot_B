package com.example.chapter4.domain.review.entity;

import com.example.chapter4.domain.store.entity.Store;
import com.example.chapter4.domain.user.entity.User;
import com.example.chapter4.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
        name = "reviews",
        indexes = {
                @Index(name = "idx_reviews_user_id", columnList = "user_id"),
                @Index(name = "idx_reviews_store_id", columnList = "store_id")
        }
)
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @Column(name = "rating", nullable = false)
    private Integer rating;  // 1~5 권장 (서비스/DTO 레벨에서 검증)

    @Column(name = "content", length = 500)
    private String content;  // 선택 입력

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User writer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Builder
    public Review(Integer rating, String content, User writer, Store store) {
        this.rating = rating;
        this.content = content;
        this.writer = writer;
        this.store = store;
    }
}