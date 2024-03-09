package com.board.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;

public record CommentRequest(
        @NotEmpty @Max(255)
        String content
) {
}
