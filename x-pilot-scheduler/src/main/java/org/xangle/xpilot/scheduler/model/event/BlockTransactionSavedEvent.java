package org.xangle.xpilot.scheduler.model.event;

import java.time.LocalDateTime;

public record BlockTransactionSavedEvent(
        LocalDateTime executionTime,
        int blockSize
) {
}