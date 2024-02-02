package org.xangle.xpilot.core.model.response;

import java.time.Instant;

public record CommentInfo(
        String id,
        String rootId,
        String parentId,
        Long depth,
        Long sequence,
        String content,
        boolean isDeleted,
        Instant createdTime,
        Instant updatedTime
) {

}
