package org.xangle.xpilot.scheduler.entity.block;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Instant;

@Getter
@Document("block")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BlockMongoEntity {

    @MongoId
    private Long number;

    @Field("time")
    private Instant time;

    @Field("hash")
    private String hash;

    @Field("parent_hash")
    private String parentHash;

    @Field("miner")
    private String miner;

    @Field("size")
    private int size;

    public BlockMongoEntity(Long number, Instant time, String hash, String parentHash, String miner, int size) {
        this.number = number;
        this.time = time;
        this.hash = hash;
        this.parentHash = parentHash;
        this.miner = miner;
        this.size = size;
    }
}
