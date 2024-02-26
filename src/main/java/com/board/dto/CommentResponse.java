package com.board.dto;

import com.board.model.Comment;

public record CommentResponse(
        Long id,
        String content
) {
    public static CommentResponse  from(Comment comment) {
        return new CommentResponse(comment.getId(), comment.getContent());
    }
}
