package com.example.chapter4.domain.store.entity;

import com.example.chapter4.domain.region.entity.Region;
import com.example.chapter4.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
        name = "stores",
        indexes = {
                @Index(name = "idx_stores_region_id", columnList = "region_id")
        }
)
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @Column(name = "store_name", nullable = false, length = 100)
    private String name;

    @Column(name = "manager_number", length = 20)
    private String managerNumber;

    @Column(name = "detail_address", length = 255)
    private String detailAddress;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    @Builder
    public Store(String name, String managerNumber, String detailAddress, Region region) {
        this.name = name;
        this.managerNumber = managerNumber;
        this.detailAddress = detailAddress;
        this.region = region;
    }
}