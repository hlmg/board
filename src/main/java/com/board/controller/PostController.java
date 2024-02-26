package com.board.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.board.dto.PostCreateRequest;
import com.board.dto.PostCreateResponse;
import com.board.service.PostService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;

    @PostMapping("/posts")
    public ResponseEntity<PostCreateResponse> createPost(@RequestBody PostCreateRequest request) {
        PostCreateResponse response = PostCreateResponse.from(postService.create(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
