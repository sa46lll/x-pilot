package org.xangle.xpilot.core.repository.comment;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.xangle.xpilot.core.entity.CommentEntity;

public interface MongoCommentRepository extends MongoRepository<CommentEntity, String> {
}
