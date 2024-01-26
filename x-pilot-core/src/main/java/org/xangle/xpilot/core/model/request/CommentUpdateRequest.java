package org.xangle.xpilot.core.model.request;

import jakarta.validation.constraints.NotBlank;

public record CommentUpdateRequest(

        @NotBlank(message = "Content is mandatory")
        String content
) {
}
