package com.board.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.data.domain.Pageable;
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

    public List<Post> getAllPost(Pageable pageable) {
        return postRepository.findAll(pageable).stream()
                .filter(post -> !post.isDeleted())
                .toList();
    }

    public Post getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("게시글을 찾을 수 없습니다."));
        if (post.isDeleted()) {
            throw new IllegalArgumentException("삭제된 게시글은 조회할 수 없습니다.");
        }
        return post;
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
