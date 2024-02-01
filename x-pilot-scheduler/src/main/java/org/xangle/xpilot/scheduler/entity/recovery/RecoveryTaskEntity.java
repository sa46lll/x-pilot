package org.xangle.xpilot.scheduler.entity.recovery;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Instant;

@Getter
@Document("recovery_task")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecoveryTaskEntity {

    @MongoId(FieldType.OBJECT_ID)
    private String id;

    @Field("min_block_number")
    private Long minBlockNumber;

    @Field("max_block_number")
    private Long maxBlockNumber;

    @Field("is_completed")
    private boolean isCompleted;

    @Field("message")
    private String log;

    @Field("retry_count")
    private int retryCount;

    @CreatedDate
    private Instant createdTime;

    @LastModifiedDate
    private Instant updatedTime;

    public RecoveryTaskEntity(Long minBlockNumber, Long maxBlockNumber, boolean isCompleted, String log) {
        this.minBlockNumber = minBlockNumber;
        this.maxBlockNumber = maxBlockNumber;
        this.isCompleted = isCompleted;
        this.log = log;
    }

    public void complete() {
        this.isCompleted = true;
    }

    public void increaseRetryCount() {
        this.retryCount++;
    }
}
