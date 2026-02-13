package com.example.chapter4.domain.post.repository;

import com.example.chapter4.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}