package org.xangle.xpilot.core.repository.transaction;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.xangle.xpilot.core.entity.TransactionEntity;

import java.util.List;

public interface MongoTransactionRepository extends MongoRepository<TransactionEntity, String> {
    List<TransactionEntity> findAllByBlockNumber(Long blockNumber);

    List<TransactionEntity> findAllByBlockNumberBetween(Long startBlockNumber, Long endBlockNumber);
}

