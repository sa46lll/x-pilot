package org.xangle.xpilot.batch.entity.transaction;

import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("transactions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TransactionMongoEntity {

    @Id
    private String hash;
    private LocalDateTime blockTime;
    private String from;
    private String to;
    private Long blockNumber;
    private String blockHash;
    private Long gasPrice;
    private Long gasUsed;
    private Long transactionFee;
    private Long index;

    public TransactionMongoEntity(String hash, LocalDateTime blockTime, String from, String to, Long blockNumber, String blockHash, Long gasPrice, Long gasUsed, Long transactionFee, Long index) {
        this.hash = hash;
        this.blockTime = blockTime;
        this.from = from;
        this.to = to;
        this.blockNumber = blockNumber;
        this.blockHash = blockHash;
        this.gasPrice = gasPrice;
        this.gasUsed = gasUsed;
        this.transactionFee = transactionFee;
        this.index = index;
    }
}
