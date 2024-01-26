package org.xangle.xpilot.scheduler.mapper;

import org.xangle.xpilot.scheduler.service.ByteConverterService;
import org.xangle.xpilot.scheduler.entity.transaction.TransactionJpaEntity;
import org.xangle.xpilot.scheduler.entity.transaction.TransactionMongoEntity;

public class TransactionMongoMapper {

    private TransactionMongoMapper() {
    }

    public static TransactionMongoEntity toEntity(TransactionJpaEntity transactionJpaEntity) {
        return new TransactionMongoEntity(
                ByteConverterService.convertToString(transactionJpaEntity.getHash()),
                transactionJpaEntity.getBlockTime(),
                ByteConverterService.convertToString(transactionJpaEntity.getFrom()),
                ByteConverterService.convertToString(transactionJpaEntity.getTo()),
                transactionJpaEntity.getBlockNumber().getNumber(),
                ByteConverterService.convertToString(transactionJpaEntity.getBlockHash()),
                transactionJpaEntity.getGasPrice(),
                transactionJpaEntity.getGasUsed(),
                transactionJpaEntity.getGasPrice() * transactionJpaEntity.getGasUsed(),
                transactionJpaEntity.getIndex()
        );
    }
}
