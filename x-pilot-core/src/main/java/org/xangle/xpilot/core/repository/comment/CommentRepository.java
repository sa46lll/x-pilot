package org.xangle.xpilot.core.repository.comment;

import org.xangle.xpilot.core.entity.CommentEntity;

import java.util.List;

public interface CommentRepository {

    void bulkInsert(List<CommentEntity> comments);
}
