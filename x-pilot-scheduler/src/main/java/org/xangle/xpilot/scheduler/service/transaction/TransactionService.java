package org.xangle.xpilot.scheduler.service.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xangle.xpilot.scheduler.entity.transaction.TransactionJpaEntity;
import org.xangle.xpilot.scheduler.mapper.TransactionMongoMapper;
import org.xangle.xpilot.scheduler.repository.transaction.TransactionMongoRepository;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionMongoRepository transactionMongoRepository;

    @Transactional
    public void save(TransactionJpaEntity transaction) {
        transactionMongoRepository.save(TransactionMongoMapper.toEntity(transaction));
    }
}
