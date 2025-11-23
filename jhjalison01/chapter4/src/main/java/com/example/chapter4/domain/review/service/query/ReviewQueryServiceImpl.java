package com.example.chapter4.domain.review.service.query;


import com.example.chapter4.domain.member.entity.Member;
import com.example.chapter4.domain.member.repository.MemberRepository;
import com.example.chapter4.domain.review.dto.ReviewSearchCondition;
import com.example.chapter4.domain.review.dto.req.ReviewRequestDto;
import com.example.chapter4.domain.review.dto.res.ReviewResponseDto;
import com.example.chapter4.domain.review.entity.QReview;
import com.example.chapter4.domain.review.entity.Review;
import com.example.chapter4.domain.review.repository.ReviewRepository;
import com.example.chapter4.domain.store.entity.QLocation;
import com.example.chapter4.domain.store.entity.Store;
import com.example.chapter4.domain.store.repository.StoreRepository;
import com.querydsl.core.BooleanBuilder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewQueryServiceImpl implements ReviewQueryService { //GET 요청 전용 service
    private final ReviewRepository reviewRepository;

    @Override
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

    public List<ReviewResponseDto.ReviewDetailDto> getMemberReviews(Long memberId, ReviewSearchCondition condition) {
        return reviewRepository.searchMemberReviews(memberId, condition);
    }
}
