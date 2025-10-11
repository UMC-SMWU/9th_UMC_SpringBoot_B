package com.example.umc9th.domain.store.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "location")
public class Location {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long locationId;

    @Column(nullable = false, length = 30)
    private String name;

}
