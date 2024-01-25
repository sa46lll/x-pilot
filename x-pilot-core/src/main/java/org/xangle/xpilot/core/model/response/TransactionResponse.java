package org.xangle.xpilot.core.model.response;

import org.xangle.xpilot.core.entity.TransactionEntity;

import java.time.Duration;
import java.time.LocalDateTime;

public record TransactionResponse(
        String transactionHash,
        long ageInSeconds,
        String from,
        String to,
        Long transactionFee
) {
    public static TransactionResponse from(TransactionEntity transactionEntity) {
        return new TransactionResponse(
                transactionEntity.getHash(),
                Duration.between(transactionEntity.getBlockTime(), LocalDateTime.now()).getSeconds(),
                transactionEntity.getFrom(),
                transactionEntity.getTo(),
                transactionEntity.getTransactionFee()
        );
    }
}
