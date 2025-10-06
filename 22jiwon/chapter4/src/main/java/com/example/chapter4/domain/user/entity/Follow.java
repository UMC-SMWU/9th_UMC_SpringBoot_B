package com.example.chapter4.domain.user.entity;

import com.example.chapter4.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
        name = "follows",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_follows_pair", columnNames = {"follower_user_id", "followed_user_id"})
        },
        indexes = {
                @Index(name = "idx_follows_follower", columnList = "follower_user_id"),
                @Index(name = "idx_follows_followed", columnList = "followed_user_id")
        }
)
public class Follow extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "follower_user_id", nullable = false)
    private User follower;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "followed_user_id", nullable = false)
    private User followed;

    @Builder
    public Follow(User follower, User followed) {
        this.follower = follower;
        this.followed = followed;
    }
}