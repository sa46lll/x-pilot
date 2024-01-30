package org.xangle.xpilot.scheduler.repository.block;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.BulkOperations.BulkMode;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.xangle.xpilot.scheduler.entity.block.BlockMongoEntity;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BlockRepository {

    private final MongoTemplate mongoTemplate;

    public void bulkInsert(List<BlockMongoEntity> blocks) {
        BulkOperations bulkOperations = mongoTemplate.bulkOps(BulkMode.UNORDERED, BlockMongoEntity.class);

        for (BlockMongoEntity block : blocks) {
            bulkOperations.insert(block);
        }

        bulkOperations.execute();
    }
}
