package org.xangle.xpilot.batch.repository.transaction;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.xangle.xpilot.batch.entity.transaction.TransactionMongoEntity;

public interface TransactionMongoRepository extends MongoRepository<TransactionMongoEntity, String> {

}
