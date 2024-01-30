package org.xangle.xpilot.core.repository.worker;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.xangle.xpilot.core.entity.WorkerEntity;

import java.util.Optional;

public interface MongoWorkerRepository extends MongoRepository<WorkerEntity, String> {
    boolean existsWorkerEntityByEmail(String email);

    Optional<WorkerEntity> findByEmail(String email);
}
