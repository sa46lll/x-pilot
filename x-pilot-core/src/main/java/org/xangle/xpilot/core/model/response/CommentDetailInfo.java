package org.xangle.xpilot.core.model.response;

import org.xangle.xpilot.core.entity.CommentEntity;
import org.xangle.xpilot.core.model.ContextHandler;

import java.time.LocalDateTime;
import java.util.Objects;

public record CommentDetailInfo(
        String workerId,
        String content,
        LocalDateTime createTime,
        boolean canModify,
        boolean isDeleted
) {
    public static CommentDetailInfo of(CommentEntity commentEntity) {
        return new CommentDetailInfo(
                commentEntity.getWorkerId(),
                commentEntity.getContent(),
                commentEntity.getCreatedTime(),
                Objects.equals(commentEntity.getWorkerId(), ContextHandler.getWorkerId()),
                commentEntity.isDeleted()
        );
    }
}
