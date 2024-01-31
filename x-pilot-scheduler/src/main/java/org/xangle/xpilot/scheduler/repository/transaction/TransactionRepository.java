package org.xangle.xpilot.scheduler.repository.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.BulkOperations.BulkMode;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.xangle.xpilot.scheduler.entity.transaction.TransactionMongoEntity;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TransactionRepository {

    private final MongoTemplate mongoTemplate;

    public void bulkInsert(List<TransactionMongoEntity> transactions) {
        BulkOperations bulkOperations = mongoTemplate.bulkOps(BulkMode.UNORDERED, TransactionMongoEntity.class);

        for (TransactionMongoEntity transaction : transactions) {
            bulkOperations.insert(transaction);
        }

        bulkOperations.execute();
    }
}
