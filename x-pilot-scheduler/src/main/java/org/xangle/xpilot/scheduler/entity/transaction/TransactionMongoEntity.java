package org.xangle.xpilot.scheduler.entity.transaction;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Document("transaction")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TransactionMongoEntity {

    @MongoId
    private String hash;

    @Field("block_time")
    private Instant blockTime;

    @Field("from")
    private String from;

    @Field("to")
    private String to;

    @Field("block_number")
    private Long blockNumber;

    @Field("block_hash")
    private String blockHash;

    @Field("gas_price")
    private Long gasPrice;

    @Field("gas_used")
    private Long gasUsed;

    @Field("transaction_fee")
    private BigDecimal transactionFee;

    @Field("index")
    private Long index;

    public TransactionMongoEntity(String hash, Instant blockTime, String from, String to, Long blockNumber, String blockHash, Long gasPrice, Long gasUsed, BigDecimal transactionFee, Long index) {
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
