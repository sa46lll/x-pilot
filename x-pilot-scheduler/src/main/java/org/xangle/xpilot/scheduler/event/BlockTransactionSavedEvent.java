package org.xangle.xpilot.scheduler.event;

import java.time.LocalDateTime;

public record BlockTransactionSavedEvent(
        LocalDateTime executionTime,
        int blockSize
) {
}