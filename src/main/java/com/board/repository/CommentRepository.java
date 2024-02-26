package com.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.board.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
