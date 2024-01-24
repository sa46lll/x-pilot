package org.xangle.xpilot.core.mapper.comment;

import org.xangle.xpilot.core.entity.CommentEntity;

public class CommentEntityMapper {

    private CommentEntityMapper() {
    }

    public static CommentEntity toEntity(final String workerId,
                                         final Long blockNumber,
                                         final String rootId,
                                         final String parentId,
                                         final Long depth,
                                         final String content) {
        return new CommentEntity(blockNumber, workerId, rootId, parentId, depth, content);
    }
}
