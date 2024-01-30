package org.xangle.xpilot.scheduler.repository.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xangle.xpilot.scheduler.entity.transaction.TransactionJpaEntity;

import java.util.List;

public interface TransactionJpaRepository extends JpaRepository<TransactionJpaEntity, String>, CustomTransactionJpaRepository {

    List<TransactionJpaEntity> findAllByBlockNumberBetween(Long minBlockNumber, Long maxBlockNumber);
}
