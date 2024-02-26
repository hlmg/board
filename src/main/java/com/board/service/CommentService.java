package com.board.service;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.board.dto.CommentRequest;
import com.board.model.Comment;
import com.board.model.Post;
import com.board.repository.CommentRepository;
import com.board.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CommentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public Comment create(Long postId, CommentRequest request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NoSuchElementException("게시글을 찾을 수 없습니다."));
        if (post.isDeleted()) {
            throw new IllegalStateException("삭제된 게시글에 댓글을 작성할 수 없습니다.");
        }
        Comment comment = Comment.builder()
                .content(request.content())
                .post(post)
                .build();
        return commentRepository.save(comment);
    }

    @Transactional
    public Comment update(Long id, CommentRequest commentRequest) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("댓글을 찾을 수 없습니다."));
        comment.update(commentRequest.content());
        return comment;
    }

    @Transactional
    public void delete(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("댓글을 찾을 수 없습니다."));
        comment.delete();
    }
}
