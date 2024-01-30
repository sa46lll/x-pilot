package org.xangle.xpilot.core.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CommentRequest(

        @NotNull(message = "Parent ID is mandatory. If you want to create a root comment, please use empty string.")
        String parentId,

        @NotBlank(message = "Content is mandatory")
        String content
) {
}
