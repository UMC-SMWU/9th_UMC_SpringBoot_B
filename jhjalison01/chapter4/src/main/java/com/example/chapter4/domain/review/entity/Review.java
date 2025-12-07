package com.example.chapter4.domain.review.entity;


import com.example.chapter4.domain.member.entity.Member;
import com.example.chapter4.domain.store.entity.Store;
import com.example.chapter4.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "review")
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content",nullable = false)
    private String content;

    @Column(name = "rating",nullable = false)
    private Float rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "review")
    private List<Reply> replyList = new ArrayList<>();

    @OneToMany(mappedBy = "review")
    private List<ReviewPhoto> reviewPhotoList = new ArrayList<>();
}
