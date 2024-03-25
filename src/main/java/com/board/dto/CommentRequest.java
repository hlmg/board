package com.board.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record CommentRequest(
        @NotEmpty @Size(max = 255)
        String content
) {
}
