package org.xangle.xpilot.batch.mapper;

import org.xangle.xpilot.batch.converter.ByteConverter;
import org.xangle.xpilot.batch.entity.transaction.TransactionJpaEntity;
import org.xangle.xpilot.batch.entity.transaction.TransactionMongoEntity;

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
