package org.xangle.xpilot.scheduler.model.event;

import java.time.LocalDateTime;

public record RecoveryTaskCompletedEvent(
        LocalDateTime executionTime,
        long taskCount,
        long minBlockNumber,
        long maxBlockNumber
) {
    public static RecoveryTaskCompletedEvent of(long taskCount, long minBlockNumber, long maxBlockNumber) {
        return new RecoveryTaskCompletedEvent(
                LocalDateTime.now(),
                taskCount,
                minBlockNumber,
                maxBlockNumber
        );
    }
}
