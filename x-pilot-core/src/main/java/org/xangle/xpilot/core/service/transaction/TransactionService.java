package org.xangle.xpilot.core.service.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.xangle.xpilot.core.entity.TransactionEntity;
import org.xangle.xpilot.core.repository.transaction.MongoTransactionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final MongoTransactionRepository mongoTransactionRepository;

    public List<TransactionEntity> getAllByBlockNumber(Long blockNumber) {
        return mongoTransactionRepository.findAllByBlockNumber(blockNumber);
    }
}
