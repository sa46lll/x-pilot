package org.xangle.xpilot.batch.mapper;

import org.xangle.xpilot.batch.entity.transaction.TransactionJpaEntity;
import org.xangle.xpilot.batch.entity.transaction.TransactionMongoEntity;

public class TransactionMongoMapper {

    private TransactionMongoMapper() {
    }

    public static TransactionMongoEntity toEntity(TransactionJpaEntity transactionJpaEntity) {
        return new TransactionMongoEntity(
                transactionJpaEntity.getHash(),
                transactionJpaEntity.getBlockTime(),
                transactionJpaEntity.getFrom(),
                transactionJpaEntity.getTo(),
                transactionJpaEntity.getBlockNumber().getNumber(),
                transactionJpaEntity.getBlockHash(),
                transactionJpaEntity.getGasPrice(),
                transactionJpaEntity.getGasUsed(),
                transactionJpaEntity.getGasPrice() * transactionJpaEntity.getGasUsed(),
                transactionJpaEntity.getIndex()
        );
    }
}
