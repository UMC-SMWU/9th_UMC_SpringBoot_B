package com.example.chapter4.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

@Getter
@AllArgsConstructor
public class PostListResponseDto {
    private final List<PostResponseDto> content;
}
