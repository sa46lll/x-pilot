package org.xangle.xpilot.batch.service.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.xangle.xpilot.batch.entity.transaction.TransactionJpaEntity;
import org.xangle.xpilot.batch.entity.transaction.TransactionMongoEntity;
import org.xangle.xpilot.batch.repository.transaction.TransactionJpaRepository;
import org.xangle.xpilot.batch.repository.transaction.TransactionMongoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionJpaRepository transactionJpaRepository;
    private final TransactionMongoRepository transactionMongoRepository;

    public List<TransactionJpaEntity> findAllAfterBlockNumber(Long blockNumber) {
        return transactionJpaRepository.findAllByBlockNumberAfter(blockNumber);
    }

    public void saveAll(List<TransactionMongoEntity> transactions) {
        transactionMongoRepository.saveAll(transactions);
    }
}
