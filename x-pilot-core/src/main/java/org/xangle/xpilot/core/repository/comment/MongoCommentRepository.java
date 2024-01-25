package org.xangle.xpilot.core.repository.comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.xangle.xpilot.core.entity.CommentEntity;

public interface MongoCommentRepository extends MongoRepository<CommentEntity, String> {
    Page<CommentEntity> findAllByBlockNumberAndRootId(Long blockNumber, String rootId, Pageable pageable);
}
