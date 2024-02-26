package com.board.dto;

import com.board.model.Post;

public record PostListResponse(Long id, String title) {
    public static PostListResponse from(Post post) {
        return new PostListResponse(post.getId(), post.getTitle());
    }
}
