package org.xangle.xpilot.core.repository.block;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.xangle.xpilot.core.entity.BlockEntity;

public interface MongoBlockRepository extends MongoRepository<BlockEntity, String> {
    Page<BlockEntity> findAll(final Pageable pageable);
}
