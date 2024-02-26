package com.board.dto;

public record PostCreateRequest(
        String title,
        String content
) {
}
