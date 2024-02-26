package com.board.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.board.dto.PostRequest;
import com.board.dto.PostResponse;
import com.board.service.PostService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;

    @PostMapping("/posts")
    public ResponseEntity<PostResponse> createPost(@RequestBody PostRequest request) {
        PostResponse response = PostResponse.from(postService.create(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<PostResponse> updatePost(@PathVariable("id") Long id, @RequestBody PostRequest request) {
        PostResponse response = PostResponse.from(postService.update(id, request));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable("id") Long id) {
        postService.delete(id);
        return ResponseEntity.ok().build();
    }
}
