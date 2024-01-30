package org.xangle.xpilot.core.model.response;

import org.xangle.xpilot.core.entity.CommentEntity;

public record CommentInfo(
        String id,
        String rootId,
        String parentId,
        Long depth,
        Long sequence,
        String content
) {

    public static CommentInfo from(CommentEntity comment) {
        return new CommentInfo(
                comment.getId(),
                comment.getRootId(),
                comment.getParentId(),
                comment.getDepth(),
                comment.getSequence(),
                comment.getContent()
        );
    }
}
