package com.board.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.board.dto.CommentRequest;
import com.board.dto.CommentResponse;
import com.board.service.CommentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/posts/{id}/comments")
    public ResponseEntity<CommentResponse> createComment(@PathVariable("id") Long postId,
            @RequestBody CommentRequest commentRequest) {
        CommentResponse response = CommentResponse.from(commentService.create(postId, commentRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable("id") Long id,
            @RequestBody CommentRequest commentRequest) {
        CommentResponse response = CommentResponse.from(commentService.update(id, commentRequest));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> updateComment(@PathVariable("id") Long id) {
        commentService.delete(id);
        return ResponseEntity.ok().build();
    }
}
