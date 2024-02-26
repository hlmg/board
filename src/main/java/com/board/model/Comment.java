package com.board.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    private Post post;

    private boolean isDeleted;

    @Builder
    public Comment(String content, Post post, boolean isDeleted) {
        this.content = content;
        this.post = post;
        this.isDeleted = isDeleted;
    }

    public void update(String content) {
        if (isDeleted) {
            throw new IllegalStateException("이미 삭제된 댓글은 수정할 수 없습니다.");
        }
        this.content = content;
    }

    public void delete() {
        isDeleted = true;
    }
}
