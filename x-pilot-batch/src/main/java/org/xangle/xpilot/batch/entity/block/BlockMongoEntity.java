package org.xangle.xpilot.batch.entity.block;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Document("block")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BlockMongoEntity {

    @MongoId
    private Long number;
    private LocalDateTime time;
    private String hash;
    private String parentHash;
    private String miner;
    private int size;

    public BlockMongoEntity(Long number, LocalDateTime time, String hash, String parentHash, String miner, int size) {
        this.number = number;
        this.time = time;
        this.hash = hash;
        this.parentHash = parentHash;
        this.miner = miner;
        this.size = size;
    }
}
