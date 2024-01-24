package org.xangle.xpilot.core.mapper.comment;

import org.xangle.xpilot.core.entity.BlockEntity;
import org.xangle.xpilot.core.entity.CommentEntity;
import org.xangle.xpilot.core.model.CommentInfo;
import org.xangle.xpilot.core.model.CommentSaveDto;

public class CommentEntityMapper {

    private CommentEntityMapper() {
    }

    public static CommentEntity toEntity(String workerId,
                                         Long blockNumber,
                                         String rootId,
                                         String parentId,
                                         Long depth,
                                         String content) {
        return new CommentEntity(blockNumber, workerId, rootId, parentId, depth, content);
    }
}
