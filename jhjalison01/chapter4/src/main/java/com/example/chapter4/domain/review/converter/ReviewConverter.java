package com.example.chapter4.domain.review.converter;

import com.example.chapter4.domain.member.entity.Member;
import com.example.chapter4.domain.review.dto.req.ReviewRequestDto;
import com.example.chapter4.domain.review.dto.res.ReviewResDto;
import com.example.chapter4.domain.review.entity.Review;
import com.example.chapter4.domain.store.entity.Store;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {
    public static Review toReview(Member member, Store store, ReviewRequestDto.CreateDto request) {
        return Review.builder()
                .member(member)
                .store(store)
                .content(request.getContent())
                .rating(request.getRating())
                .build();
    }

    // result -> DTO
    public static ReviewResDto.ReviewPreViewListDto toReviewPreviewListDTO(
            Page<Review> result
    ){
        return ReviewResDto.ReviewPreViewListDto.builder()
                .reviewList(result.getContent().stream()
                        .map(ReviewConverter::toReviewPreviewDto)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static ReviewResDto.ReviewPreViewDto toReviewPreviewDto(
            Review review
    ){
        return ReviewResDto.ReviewPreViewDto.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getRating())
                .body(review.getContent())
                .createdAt(LocalDate.from(review.getCreatedAt()))
                .build();
    }

    public static ReviewResDto.ReviewDetailDto toReviewDetailDto(Review review) {
        return ReviewResDto.ReviewDetailDto.builder()
                .reviewId(review.getId())
                .storeName(review.getStore().getName())
                .rating(review.getRating())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResDto.ReviewDetailListDto toReviewPreviewListDto(Page<Review> reviewPage) {
        List<ReviewResDto.ReviewDetailDto> reviewDetailList = reviewPage.stream()
                .map(ReviewConverter::toReviewDetailDto)
                .collect(Collectors.toList());

        return ReviewResDto.ReviewDetailListDto.builder()
                .isLast(reviewPage.isLast())
                .isFirst(reviewPage.isFirst())
                .totalPage(reviewPage.getTotalPages())
                .totalElements(reviewPage.getTotalElements())
                .listSize(reviewDetailList.size())
                .reviewList(reviewDetailList)
                .build();
    }
}
