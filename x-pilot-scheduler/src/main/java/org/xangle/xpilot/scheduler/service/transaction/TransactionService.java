package org.xangle.xpilot.scheduler.service.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xangle.xpilot.scheduler.entity.transaction.TransactionJpaEntity;
import org.xangle.xpilot.scheduler.entity.transaction.TransactionMongoEntity;
import org.xangle.xpilot.scheduler.repository.transaction.TransactionMongoRepository;
import org.xangle.xpilot.scheduler.service.ByteConverterService;
import org.xangle.xpilot.scheduler.service.TransactionFeeCalculatorService;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionMongoRepository transactionMongoRepository;

    @Transactional
    public void save(TransactionJpaEntity transaction) {
        transactionMongoRepository.save(
                new TransactionMongoEntity(
                        ByteConverterService.convertToString(transaction.getHash()),
                        transaction.getBlockTime(),
                        ByteConverterService.convertToString(transaction.getFrom()),
                        ByteConverterService.convertToString(transaction.getTo()),
                        transaction.getBlockNumber().getNumber(),
                        ByteConverterService.convertToString(transaction.getBlockHash()),
                        transaction.getGasPrice(),
                        transaction.getGasUsed(),
                        TransactionFeeCalculatorService.calculate(transaction.getGasPrice(), transaction.getGasUsed()),
                        transaction.getIndex()
                )
        );
    }
}
