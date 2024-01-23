package org.xangle.xpilot.core.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Getter
@Document("block")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BlockEntity {

    @MongoId
    private Long number;
    private LocalDateTime time;
    private String hash;
    private String parentHash;
    private String miner;
    private int size;
    private int transactionCount;
}
