package org.xangle.xpilot.core.repository.worker;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.xangle.xpilot.core.entity.WorkerEntity;

public interface MongoWorkerRepository extends MongoRepository<WorkerEntity, String> {
    boolean existsWorkerEntityByEmail(String email);

    boolean existsWorkerEntityByEmailAndPassword(String email, String password);
}
