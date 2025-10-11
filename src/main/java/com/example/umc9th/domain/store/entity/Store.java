package com.example.umc9th.domain.store.entity;

import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "store",
        indexes = @Index(name="idx_store_location", columnList="location_id"))
public class Store extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 20)
    private String managerNumber;

    @Column(nullable = false, length = 30)
    private String address;

    @Column(nullable = false, length = 30)
    private String detailAddress;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "location_id", nullable = false)
    private Location location;

}

