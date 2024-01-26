package org.xangle.xpilot.core.model.response;

import org.xangle.xpilot.core.entity.TransactionEntity;

import java.time.Duration;
import java.time.LocalDateTime;

public record TransactionInfo(
        String transactionHash,
        long ageInSeconds,
        String from,
        String to,
        Long transactionFee
) {
    public static TransactionInfo from(TransactionEntity transactionEntity) {
        return new TransactionInfo(
                transactionEntity.getHash(),
                Duration.between(transactionEntity.getBlockTime(), LocalDateTime.now()).getSeconds(),
                transactionEntity.getFrom(),
                transactionEntity.getTo(),
                transactionEntity.getTransactionFee()
        );
    }
}
