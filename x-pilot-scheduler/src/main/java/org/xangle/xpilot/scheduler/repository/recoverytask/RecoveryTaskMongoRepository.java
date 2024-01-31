package org.xangle.xpilot.scheduler.repository.recoverytask;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.xangle.xpilot.scheduler.entity.recovery.RecoveryTaskEntity;

import java.util.List;

public interface RecoveryTaskMongoRepository extends MongoRepository<RecoveryTaskEntity, String> {

    List<RecoveryTaskEntity> findAllByIsCompletedAndRetryCountLessThan(boolean isCompleted, int retryCount, Pageable pageable);
}
