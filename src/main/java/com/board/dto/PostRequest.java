package com.board.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;

public record PostRequest(
        @NotEmpty @Max(50)
        String title,

        @NotEmpty @Max(1000)
        String content
) {
}
