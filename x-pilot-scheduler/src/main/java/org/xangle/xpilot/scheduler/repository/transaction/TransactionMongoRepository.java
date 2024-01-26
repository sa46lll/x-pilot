package org.xangle.xpilot.scheduler.repository.transaction;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.xangle.xpilot.scheduler.entity.transaction.TransactionMongoEntity;

public interface TransactionMongoRepository extends MongoRepository<TransactionMongoEntity, String> {

}
