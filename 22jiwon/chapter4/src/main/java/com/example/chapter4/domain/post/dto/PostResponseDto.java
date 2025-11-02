package com.example.chapter4.domain.post.dto;

import com.example.chapter4.domain.post.entity.Post;
import lombok.Getter;

@Getter
public class PostResponseDto {
    private final Long id;
    private final String title;
    private final String content;
    private final String status;
    private final Long authorId;
    private final String authorNickname;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.status = post.getStatus().name();
        this.authorId = post.getAuthor().getId();
        this.authorNickname = post.getAuthor().getUsername();
    }
}
