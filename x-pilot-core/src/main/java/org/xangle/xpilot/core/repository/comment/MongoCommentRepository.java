package org.xangle.xpilot.core.repository.comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.xangle.xpilot.core.entity.CommentEntity;

import java.util.List;
import java.util.Optional;

public interface MongoCommentRepository extends MongoRepository<CommentEntity, String> {
    Page<CommentEntity> findAllByBlockNumberAndDepth(Long blockNumber, Long depth, Pageable pageable);

    List<CommentEntity> findAllByRootIdIn(List<String> rootIds);

    Optional<CommentEntity> findFirstByParentIdOrderBySequenceDesc(String parentId);
}
