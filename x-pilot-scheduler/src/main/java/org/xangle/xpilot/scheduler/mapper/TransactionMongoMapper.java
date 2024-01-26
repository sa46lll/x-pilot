package org.xangle.xpilot.scheduler.mapper;

import org.xangle.xpilot.scheduler.converter.ByteConverter;
import org.xangle.xpilot.scheduler.entity.transaction.TransactionJpaEntity;
import org.xangle.xpilot.scheduler.entity.transaction.TransactionMongoEntity;

public class TransactionMongoMapper {

    private TransactionMongoMapper() {
    }

    public static TransactionMongoEntity toEntity(TransactionJpaEntity transactionJpaEntity) {
        return new TransactionMongoEntity(
                ByteConverter.convertToString(transactionJpaEntity.getHash()),
                transactionJpaEntity.getBlockTime(),
                ByteConverter.convertToString(transactionJpaEntity.getFrom()),
                ByteConverter.convertToString(transactionJpaEntity.getTo()),
                transactionJpaEntity.getBlockNumber().getNumber(),
                ByteConverter.convertToString(transactionJpaEntity.getBlockHash()),
                transactionJpaEntity.getGasPrice(),
                transactionJpaEntity.getGasUsed(),
                transactionJpaEntity.getGasPrice() * transactionJpaEntity.getGasUsed(),
                transactionJpaEntity.getIndex()
        );
    }
}
