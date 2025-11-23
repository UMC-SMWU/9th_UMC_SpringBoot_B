package com.example.chapter4.domain.review.service.command;

import com.example.chapter4.domain.member.entity.Member;
import com.example.chapter4.domain.member.exception.MemberException;
import com.example.chapter4.domain.member.exception.code.MemberErrorCode;
import com.example.chapter4.domain.member.repository.MemberRepository;
import com.example.chapter4.domain.review.converter.ReviewConverter;
import com.example.chapter4.domain.review.dto.req.ReviewRequestDto;
import com.example.chapter4.domain.review.entity.Review;
import com.example.chapter4.domain.review.repository.ReviewRepository;
import com.example.chapter4.domain.store.entity.Store;
import com.example.chapter4.domain.store.exception.StoreException;
import com.example.chapter4.domain.store.exception.code.StoreErrorCode;
import com.example.chapter4.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    //리뷰 작성하기
    @Override
    public Review createReview(ReviewRequestDto.CreateDto request, Long memberId) {
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        Review review = ReviewConverter.toReview(member, store, request);

        return reviewRepository.save(review);
    }
}
