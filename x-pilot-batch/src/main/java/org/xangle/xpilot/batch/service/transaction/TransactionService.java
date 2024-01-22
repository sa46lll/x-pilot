package org.xangle.xpilot.batch.service.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.xangle.xpilot.batch.entity.transaction.TransactionJpaEntity;
import org.xangle.xpilot.batch.mapper.TransactionMongoMapper;
import org.xangle.xpilot.batch.repository.transaction.TransactionMongoRepository;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionMongoRepository transactionMongoRepository;

    public void save(TransactionJpaEntity transaction) {
        transactionMongoRepository.save(TransactionMongoMapper.toEntity(transaction));
    }
}
