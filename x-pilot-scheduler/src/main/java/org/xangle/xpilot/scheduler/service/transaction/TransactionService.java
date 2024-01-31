package org.xangle.xpilot.scheduler.service.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.xangle.xpilot.scheduler.entity.transaction.TransactionJpaEntity;
import org.xangle.xpilot.scheduler.entity.transaction.TransactionMongoEntity;
import org.xangle.xpilot.scheduler.repository.transaction.TransactionJpaRepository;
import org.xangle.xpilot.scheduler.repository.transaction.TransactionRepository;
import org.xangle.xpilot.scheduler.service.ByteConverterService;
import org.xangle.xpilot.scheduler.service.TransactionFeeCalculatorService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionJpaRepository transactionJpaRepository;

    public List<TransactionJpaEntity> findAllByBlockNumberRange(Long minBlockNumber, Long maxBlockNumber) {
        return transactionJpaRepository.findAllByBlockNumberBetween(minBlockNumber, maxBlockNumber);
    }

    public void saveAll(List<TransactionJpaEntity> transactions) {
        List<TransactionMongoEntity> transactionMongoEntities = transactions.stream()
                .map(transaction -> new TransactionMongoEntity(
                        ByteConverterService.convertToString(transaction.getHash()),
                        transaction.getBlockTime(),
                        ByteConverterService.convertToString(transaction.getFrom()),
                        ByteConverterService.convertToString(transaction.getTo()),
                        transaction.getBlockNumber(),
                        ByteConverterService.convertToString(transaction.getBlockHash()),
                        transaction.getGasPrice(),
                        transaction.getGasUsed(),
                        TransactionFeeCalculatorService.calculate(transaction.getGasPrice(), transaction.getGasUsed()),
                        transaction.getIndex()
                ))
                .toList();

        transactionRepository.bulkInsert(transactionMongoEntities);
    }
}
