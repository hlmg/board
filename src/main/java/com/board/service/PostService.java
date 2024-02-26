package com.board.service;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.board.dto.PostRequest;
import com.board.model.Post;
import com.board.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Post create(PostRequest request) {
        Post post = Post.builder()
                .title(request.title())
                .content(request.content())
                .build();
        return postRepository.save(post);
    }

    @Transactional
    public void delete(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("게시글을 찾을 수 없습니다."));
        post.delete();
    }

    @Transactional
    public Post update(Long id, PostRequest request) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("게시글을 찾을 수 없습니다."));
        post.update(request.title(), request.content());
        return post;
    }
}
