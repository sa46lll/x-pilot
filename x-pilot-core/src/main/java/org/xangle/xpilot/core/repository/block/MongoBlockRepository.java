package org.xangle.xpilot.core.repository.block;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.xangle.xpilot.core.entity.BlockEntity;

import java.util.Optional;

public interface MongoBlockRepository extends MongoRepository<BlockEntity, String> {
    Page<BlockEntity> findAll(Pageable pageable);

    Optional<BlockEntity> findByNumber(Long number);
}
