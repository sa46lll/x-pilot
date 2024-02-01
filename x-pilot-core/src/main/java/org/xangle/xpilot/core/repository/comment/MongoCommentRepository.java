package org.xangle.xpilot.core.repository.comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.xangle.xpilot.core.entity.CommentEntity;

import java.util.List;
import java.util.Optional;

public interface MongoCommentRepository extends MongoRepository<CommentEntity, String> {
    Page<CommentEntity> findAllByBlockNumberAndDepth(Long blockNumber, Long depth, Pageable pageable);

    Optional<CommentEntity> findFirstByParentIdOrderBySequenceDesc(String parentId);

    Page<CommentEntity> findAllByParentId(String parentId, Pageable pageable);

    List<CommentEntity> findAllByRootId(String rootId);
}
