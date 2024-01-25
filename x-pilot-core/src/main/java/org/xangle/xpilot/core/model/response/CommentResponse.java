package org.xangle.xpilot.core.model.response;

import java.time.LocalDateTime;

public record CommentResponse(
        String workerId,
        String content,
        LocalDateTime createTime,
        boolean canModify,
        boolean isDeleted
) {
}
