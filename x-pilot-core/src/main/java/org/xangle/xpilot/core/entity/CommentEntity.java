package org.xangle.xpilot.core.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Instant;

@Getter
@Document("comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@CompoundIndexes(
        @CompoundIndex(name = "comment_index", def = "{'block_number': 1, 'root_id': 1, 'parent_id': 1, 'depth': 1, 'sequence': 1}")
)
public class CommentEntity {

    @MongoId(FieldType.OBJECT_ID)
    private String id;

    @Field("block_number")
    private Long blockNumber;

    @Field("worker_id")
    private String workerId;

    @Field("root_id")
    private String rootId; // 최상위 댓글 id

    @Field("parent_id")
    private String parentId; // 부모 댓글 id

    @Field("depth")
    private Long depth; // 최상위 댓글: 0, 대댓글: 부모 댓글의 depth + 1

    @Field("sequence")
    private Long sequence; // 첫 번째 댓글: 1, 두 번째 댓글: 2, ... (같은 부모 내)

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

    public CommentEntity(Long blockNumber, String workerId, String rootId, String parentId, Long depth, Long sequence, String content) {
        this.blockNumber = blockNumber;
        this.workerId = workerId;
        this.rootId = rootId;
        this.parentId = parentId;
        this.depth = depth;
        this.sequence = sequence;
        this.content = content;
    }

    public CommentEntity(String id, Long blockNumber, String workerId, String rootId, String parentId, Long depth, Long sequence, String content, Instant createdTime) {
        this.id = id;
        this.blockNumber = blockNumber;
        this.workerId = workerId;
        this.rootId = rootId;
        this.parentId = parentId;
        this.depth = depth;
        this.sequence = sequence;
        this.content = content;
        this.createdTime = createdTime;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void delete() {
        this.deleted = true;
    }
}
