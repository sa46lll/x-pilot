package org.xangle.xpilot.scheduler.repository.block;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.xangle.xpilot.scheduler.entity.block.BlockMongoEntity;

import java.util.Optional;

public interface BlockMongoRepository extends MongoRepository<BlockMongoEntity, String> {

    Optional<BlockMongoEntity> findTopByOrderByNumberDesc();
}