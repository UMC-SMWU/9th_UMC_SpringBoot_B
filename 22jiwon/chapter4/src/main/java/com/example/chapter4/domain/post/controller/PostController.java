package com.example.chapter4.domain.post.controller;

import com.example.chapter4.domain.post.dto.PostResponseDto;
import com.example.chapter4.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.chapter4.global.apiPayload.ApiResponse;
import com.example.chapter4.global.apiPayload.code.GeneralSuccessCode;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public ApiResponse<List<PostResponseDto>> getAllPosts() {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, postService.getAllPosts());
    }

    @GetMapping("/{postId}")
    public ApiResponse<PostResponseDto> getPost(@PathVariable Long postId) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, postService.getPost(postId));
    }
}
