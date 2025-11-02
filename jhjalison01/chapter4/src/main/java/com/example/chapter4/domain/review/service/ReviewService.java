package com.example.chapter4.domain.review.service;

import com.example.chapter4.domain.member.entity.Member;
import com.example.chapter4.domain.member.repository.MemberRepository;
import com.example.chapter4.domain.review.dto.ReviewResponseDto;
import com.example.chapter4.domain.review.dto.ReviewSearchCondition;
import com.example.chapter4.domain.review.entity.QReview;
import com.example.chapter4.domain.review.entity.Review;
import com.example.chapter4.domain.review.repository.ReviewRepository;
import com.example.chapter4.domain.store.entity.QLocation;
import com.example.chapter4.domain.store.entity.Store;
import com.example.chapter4.domain.store.repository.StoreRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    //리뷰 작성하기
    public void createReview(String content, Float rating, Long memberId, Long storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow();
        Member member = memberRepository.findById(memberId).orElseThrow();

        Review review = Review.builder()
                .content(content)
                .rating(rating)
                .store(store)
                .member(member)
                .build();

        reviewRepository.save(review);
    }

    //
    public List<Review> searchReview(String query, String type){
        QReview review = QReview.review;
        QLocation location = QLocation.location;

        BooleanBuilder builder = new BooleanBuilder();

        //동적 쿼리: 검색 조건
        if(type.equals("location")){
            builder.and(location.name.contains(query));
        }
        if(type.equals("rating")){
            builder.and(review.rating.goe(Float.parseFloat(query)));
        }
        if(type.equals("both")){
            String firstQuery = query.split("&")[0];
            String secondQuery = query.split("&")[1];

            builder.and(location.name.contains(firstQuery));
            builder.and(review.rating.goe(Float.parseFloat(secondQuery)));
        }

        List<Review> reviewList = reviewRepository.searchReview(builder);

        return reviewList;
    }

    public List<ReviewResponseDto> getMemberReviews(Long memberId, ReviewSearchCondition condition) {
        return reviewRepository.searchMemberReviews(memberId, condition);
    }
}
