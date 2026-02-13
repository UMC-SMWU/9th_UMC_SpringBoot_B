package com.example.chapter4.domain.region.entity;

import com.example.chapter4.domain.user.entity.User;
import com.example.chapter4.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
        name = "user_region_bonus",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_user_region", columnNames = {"user_id", "region_id"})
        },
        indexes = {
                @Index(name = "idx_urb_user", columnList = "user_id"),
                @Index(name = "idx_urb_region", columnList = "region_id")
        }
)
public class UserRegionBonus extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "urb_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    @Column(name = "bonus_point", nullable = false)
    private Integer bonusPoint;

    @Builder
    public UserRegionBonus(User user, Region region, Integer bonusPoint) {
        this.user = user;
        this.region = region;
        this.bonusPoint = bonusPoint;
    }
}