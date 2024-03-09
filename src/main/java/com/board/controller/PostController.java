package com.board.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.board.dto.PostListResponse;
import com.board.dto.PostRequest;
import com.board.dto.PostResponse;
import com.board.service.PostService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/posts")
    public PostResponse createPost(@RequestBody PostRequest request) {
        return PostResponse.from(postService.create(request));
    }

    @GetMapping("/posts")
    public List<PostListResponse> getAllPost(
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return postService.getAllPost(pageable)
                .stream()
                .map(PostListResponse::from)
                .toList();
    }

    @GetMapping("/posts/{id}")
    public PostResponse getPost(@PathVariable("id") Long id) {
        return PostResponse.from(postService.getPost(id));
    }

    @PutMapping("/posts/{id}")
    public PostResponse updatePost(@PathVariable("id") Long id, @RequestBody PostRequest request) {
        return PostResponse.from(postService.update(id, request));
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable("id") Long id) {
        postService.delete(id);
    }
}
