package org.xangle.xpilot.core.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Getter
@Document("block")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BlockEntity {

    @MongoId
    private Long number;

    @Field("time")
    private LocalDateTime time;

    @Field("hash")
    private String hash;

    @Field("parent_hash")
    private String parentHash;

    @Field("miner")
    private String miner;

    @Field("size")
    private int size;

    @Field("transaction_count")
    private int transactionCount;
}
