package com.board.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record PostRequest(
        @NotEmpty @Size(max = 50)
        String title,

        @NotEmpty @Size(max = 1000)
        String content
) {
}
