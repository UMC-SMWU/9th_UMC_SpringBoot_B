package com.example.chapter4.domain.review.service.query;


import com.example.chapter4.domain.member.entity.Member;
import com.example.chapter4.domain.member.exception.MemberException;
import com.example.chapter4.domain.member.exception.code.MemberErrorCode;
import com.example.chapter4.domain.member.repository.MemberRepository;
import com.example.chapter4.domain.review.converter.ReviewConverter;
import com.example.chapter4.domain.review.dto.ReviewSearchCondition;
import com.example.chapter4.domain.review.dto.res.ReviewResDto;
import com.example.chapter4.domain.review.entity.QReview;
import com.example.chapter4.domain.review.entity.Review;
import com.example.chapter4.domain.review.repository.ReviewRepository;
import com.example.chapter4.domain.store.entity.QLocation;
import com.example.chapter4.domain.store.entity.Store;
import com.example.chapter4.domain.store.exception.StoreException;
import com.example.chapter4.domain.store.exception.code.StoreErrorCode;
import com.example.chapter4.domain.store.repository.StoreRepository;
import com.querydsl.core.BooleanBuilder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewQueryServiceImpl implements ReviewQueryService { //GET 요청 전용 service
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

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

    public ReviewResDto.ReviewDetailListDto getMemberReviews(Long memberId, ReviewSearchCondition condition) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()->new MemberException(MemberErrorCode.NOT_FOUND));

        int pageIndex = condition.getPage() - 1;
        Pageable pageable = PageRequest.of(pageIndex, 10);

        // Repository에서 Page<Review> 반환
        Page<Review> reviewPage = reviewRepository.searchMemberReviews(memberId, condition, pageable);

        return ReviewConverter.toReviewPreviewListDto(reviewPage);
    }

    @Override
    public ReviewResDto.ReviewPreViewListDto findReview(String storeName, Integer page){
        Store store = storeRepository.findByName(storeName)
                .orElseThrow(()-> new StoreException(StoreErrorCode.NOT_FOUND));

        //- 가게에 맞는 리뷰를 가져온다 (Offset 페이징)
        PageRequest pageRequest = PageRequest.of(page, 5);
        Page<Review> result = reviewRepository.findAllByStore(store, pageRequest);

        //- 결과를 응답 DTO로 변환한다 (컨버터 이용)
        return ReviewConverter.toReviewPreviewListDTO(result);

    }
}
