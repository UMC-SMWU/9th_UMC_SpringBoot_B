package com.example.chapter4.domain.review.service;

import com.example.chapter4.domain.member.entity.Member;
import com.example.chapter4.domain.member.repository.MemberRepository;
import com.example.chapter4.domain.review.entity.Review;
import com.example.chapter4.domain.review.repository.ReviewRepository;
import com.example.chapter4.domain.store.entity.Store;
import com.example.chapter4.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    //리뷰 작성하기
    public void createReview(String content, Float rating, Long memberId, Long storeId) {
        Store store = storeRepository.findById(memberId).orElseThrow();
        Member member = memberRepository.findById(storeId).orElseThrow();

        Review review = Review.builder()
                .content(content)
                .rating(rating)
                .store(store)
                .member(member)
                .build();

        reviewRepository.save(review);
    }
}
