package com.example.chapter4.domain.mission.entity;

import com.example.chapter4.domain.user.entity.User;
import com.example.chapter4.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
        name = "user_mission_completions",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_user_mission", columnNames = {"user_id", "mission_id"})
        },
        indexes = {
                @Index(name = "idx_umc_user", columnList = "user_id"),
                @Index(name = "idx_umc_mission", columnList = "mission_id")
        }
)
public class UserMissionCompletion extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "umc_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @Column(name = "completed_at", nullable = false)
    private LocalDateTime completedAt;

    @Builder
    public UserMissionCompletion(User user, Mission mission, LocalDateTime completedAt) {
        this.user = user;
        this.mission = mission;
        this.completedAt = completedAt;
    }
}