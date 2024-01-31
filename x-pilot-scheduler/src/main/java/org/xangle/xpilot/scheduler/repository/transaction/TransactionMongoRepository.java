package org.xangle.xpilot.scheduler.repository.transaction;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.xangle.xpilot.scheduler.entity.transaction.TransactionMongoEntity;

import java.util.List;
import java.util.Optional;

public interface TransactionMongoRepository extends MongoRepository<TransactionMongoEntity, String> {

    List<TransactionMongoEntity> findAllByBlockNumberBetween(Long minBlockNumber, Long maxBlockNumber);

    Optional<TransactionMongoEntity> findTopByOrderByBlockNumberDesc();
}