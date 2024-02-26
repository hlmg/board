package com.board.controller;

import java.util.List;

import org.hibernate.query.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.board.dto.PostRequest;
import com.board.dto.PostResponse;
import com.board.dto.PostListResponse;
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

    @GetMapping("/posts")
    public ResponseEntity<List<PostListResponse>> getAllPost(
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        List<PostListResponse> response = postService.getAllPost(pageable)
                .stream()
                .map(PostListResponse::from)
                .toList();
        return ResponseEntity.ok(response);
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
