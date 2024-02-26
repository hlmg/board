package com.board.dto;

import java.util.Set;

import com.board.model.Comment;
import com.board.model.Post;

public record PostResponse(
        Long id,
        String title,
        String content,
        Set<Comment> comments) {
    public static PostResponse from(Post post) {
        return new PostResponse(post.getId(), post.getTitle(), post.getContent(), post.getComments());
    }
}
