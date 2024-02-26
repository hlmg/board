package com.board.dto;

import java.util.Set;
import java.util.stream.Collectors;

import com.board.model.Post;

public record PostResponse(
        Long id,
        String title,
        String content,
        Set<CommentResponse> comments) {
    public static PostResponse from(Post post) {
        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getComments().stream()
                        .map(CommentResponse::from)
                        .collect(Collectors.toSet())
        );
    }
}
