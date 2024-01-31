package org.xangle.xpilot.core.model.response;

import org.xangle.xpilot.core.entity.WorkerEntity;

public record SignupInfo(
        String workerId,
        String email,
        String name
) {
    public static SignupInfo from(WorkerEntity worker) {
        return new SignupInfo(
                worker.getId(),
                worker.getEmail(),
                worker.getName()
        );
    }
}
