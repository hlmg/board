package com.board.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.board.dto.CommentRequest;
import com.board.dto.CommentResponse;
import com.board.service.CommentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentService commentService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/posts/{id}/comments")
    public CommentResponse createComment(@PathVariable("id") Long postId,
            @Valid @RequestBody CommentRequest commentRequest) {
        return CommentResponse.from(commentService.create(postId, commentRequest));
    }

    @PutMapping("/comments/{id}")
    public CommentResponse updateComment(@PathVariable("id") Long id,
            @Valid @RequestBody CommentRequest commentRequest) {
        return CommentResponse.from(commentService.update(id, commentRequest));
    }

    @DeleteMapping("/comments/{id}")
    public void updateComment(@PathVariable("id") Long id) {
        commentService.delete(id);
    }
}
