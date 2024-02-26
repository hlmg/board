package com.board.dto;

import com.board.model.Post;

public record PostCreateResponse(
        Long id,
        String title,
        String content
) {
    public static PostCreateResponse from(Post post) {
        return new PostCreateResponse(post.getId(), post.getTitle(), post.getContent());
    }
}
