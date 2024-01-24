package org.xangle.xpilot.batch.entity.block;

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
public class BlockMongoEntity {

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

    public BlockMongoEntity(Long number, LocalDateTime time, String hash, String parentHash, String miner, int size, int transactionCount) {
        this.number = number;
        this.time = time;
        this.hash = hash;
        this.parentHash = parentHash;
        this.miner = miner;
        this.size = size;
        this.transactionCount = transactionCount;
    }
}
