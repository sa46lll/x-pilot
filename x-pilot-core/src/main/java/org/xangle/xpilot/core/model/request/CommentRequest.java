package org.xangle.xpilot.core.model.request;

import jakarta.validation.constraints.NotBlank;

public record CommentRequest(

        @NotBlank(message = "Parent ID is mandatory")
        String parentId,

        @NotBlank(message = "Content is mandatory")
        String content
) {
}
