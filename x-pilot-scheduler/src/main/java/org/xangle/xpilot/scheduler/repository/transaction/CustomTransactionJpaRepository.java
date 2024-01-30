package org.xangle.xpilot.scheduler.repository.transaction;

import org.xangle.xpilot.scheduler.entity.transaction.TransactionJpaEntity;

import java.time.Instant;
import java.util.List;

public interface CustomTransactionJpaRepository {

    List<TransactionJpaEntity> findAllByBlockTimeAndBlockNumber(Instant blockTime, Long blockNumber);
}
