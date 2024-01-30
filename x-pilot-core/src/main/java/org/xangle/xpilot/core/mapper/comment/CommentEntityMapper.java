package org.xangle.xpilot.core.mapper.comment;

import org.xangle.xpilot.core.entity.CommentEntity;

public class CommentEntityMapper {

    private CommentEntityMapper() {
    }

    public static CommentEntity toEntity(String workerId, Long blockNumber, String rootId, String parentId,
                                         Long depth,
                                         Long sequence,
                                         String content) {
        return new CommentEntity(blockNumber, workerId, rootId, parentId, depth, sequence, content);
    }
}
