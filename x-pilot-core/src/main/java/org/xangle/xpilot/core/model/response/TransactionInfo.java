package org.xangle.xpilot.core.model.response;

import org.xangle.xpilot.core.entity.TransactionEntity;
import org.xangle.xpilot.core.service.DateUtilService;

import java.math.BigDecimal;

public record TransactionInfo(
        String transactionHash,
        String age,
        String from,
        String to,
        BigDecimal transactionFee
) {
    public static TransactionInfo from(TransactionEntity transactionEntity) {
        return new TransactionInfo(
                transactionEntity.getHash(),
                DateUtilService.getAge(transactionEntity.getBlockTime()),
                transactionEntity.getFrom(),
                transactionEntity.getTo(),
                transactionEntity.getTransactionFee()
        );
    }
}
