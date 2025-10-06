package com.example.chapter4.domain.mission.entity;

import com.example.chapter4.domain.store.entity.Store;
import com.example.chapter4.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
        name = "missions",
        indexes = {
                @Index(name = "idx_missions_store_id", columnList = "store_id")
        }
)
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long id;

    @Column(name = "mission_name", nullable = false, length = 100)
    private String name;

    @Column(name = "mission_desc", nullable = false, length = 255)
    private String description;

    @Column(name = "point_awarded", nullable = false)
    private Integer pointAwarded;

    @Column(name = "deadline", nullable = false)
    private LocalDateTime deadline;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Builder
    public Mission(String name, String description, Integer pointAwarded,
                   LocalDateTime deadline, Store store) {
        this.name = name;
        this.description = description;
        this.pointAwarded = pointAwarded;
        this.deadline = deadline;
        this.store = store;
    }
}