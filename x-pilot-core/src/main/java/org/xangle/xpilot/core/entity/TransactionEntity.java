package org.xangle.xpilot.core.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Document("transaction")
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class TransactionEntity {

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
}
