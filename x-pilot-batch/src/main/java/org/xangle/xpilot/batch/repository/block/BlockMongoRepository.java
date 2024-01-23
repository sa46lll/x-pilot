package org.xangle.xpilot.batch.repository.block;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.xangle.xpilot.batch.entity.block.BlockJpaEntity;
import org.xangle.xpilot.batch.entity.block.BlockMongoEntity;

import java.util.Optional;

public interface BlockMongoRepository extends MongoRepository<BlockMongoEntity, String> {

    Optional<BlockMongoEntity> findTopByOrderByNumberDesc();
}