package org.xangle.xpilot.core.mapper.worker;

import org.xangle.xpilot.core.entity.WorkerEntity;
import org.xangle.xpilot.core.model.request.SignupRequest;

public class WorkerEntityMapper {

    private WorkerEntityMapper() {
    }

    public static WorkerEntity toEntity(SignupRequest signupRequest) {
        return new WorkerEntity(
                signupRequest.email(),
                signupRequest.name(),
                signupRequest.password()
        );
    }
}
