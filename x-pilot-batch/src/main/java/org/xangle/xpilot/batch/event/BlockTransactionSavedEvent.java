package org.xangle.xpilot.batch.event;

import java.time.LocalDateTime;

public record BlockTransactionSavedEvent(
        LocalDateTime executionTime,
        int blockSize
) {
}