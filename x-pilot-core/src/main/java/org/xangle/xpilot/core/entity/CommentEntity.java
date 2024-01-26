package org.xangle.xpilot.core.entity;

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
@Document("comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentEntity {

    @MongoId(FieldType.OBJECT_ID)
    private String id;

    @Field("block_number")
    private Long blockNumber;

    @Field("worker_id")
    private String workerId;

    @Field("root_id")
    private String rootId;

    @Field("parent_id")
    private String parentId;

    @Field("depth")
    private Long depth;

    @Field("content")
    private String content;

    @Field("deleted")
    private boolean deleted;

    @CreatedDate
    @Field("created_time")
    private Instant createdTime;

    @LastModifiedDate
    @Field("updated_time")
    private Instant updatedTime;

    public CommentEntity(Long blockNumber, String workerId, String rootId, String parentId, Long depth, String content) {
        this.blockNumber = blockNumber;
        this.workerId = workerId;
        this.rootId = rootId;
        this.parentId = parentId;
        this.depth = depth;
        this.content = content;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void delete() {
        this.deleted = true;
    }
}
