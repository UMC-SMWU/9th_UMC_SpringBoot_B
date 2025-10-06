package com.example.chapter4.domain.region.entity;

import com.example.chapter4.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
        name = "regions",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_regions_region_name", columnNames = "region_name")
        }
)
public class Region extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private Long id;

    @Column(name = "region_name", nullable = false, length = 100)
    private String name;

    @Builder
    public Region(String name) {
        this.name = name;
    }
}