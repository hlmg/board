package com.board.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 1000)
    private String content;

    @OneToMany(mappedBy = "post")
    private Set<Comment> comments = new HashSet<>();

    private boolean isDeleted;

    @Builder
    private Post(String title, String content, Set<Comment> comments, boolean isDeleted) {
        this.title = title;
        this.content = content;
        if (comments == null) {
            comments = Collections.emptySet();
        }
        this.comments = comments;
        this.isDeleted = isDeleted;
    }

    public void delete() {
        isDeleted = true;
        comments.forEach(Comment::delete);
    }

    public void update(String title, String content) {
        if (isDeleted) {
            throw new IllegalArgumentException("이미 삭제된 게시글은 수정할 수 없습니다.");
        }
        this.title = title;
        this.content = content;
    }
}
