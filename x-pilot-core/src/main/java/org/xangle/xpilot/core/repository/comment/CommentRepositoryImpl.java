package org.xangle.xpilot.core.repository.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.xangle.xpilot.core.entity.CommentEntity;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public void bulkInsert(List<CommentEntity> comments) {
        BulkOperations bulkOperations = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, CommentEntity.class);

        for (CommentEntity comment : comments) {
            bulkOperations.insert(comment);
        }

        bulkOperations.execute();
    }
}
