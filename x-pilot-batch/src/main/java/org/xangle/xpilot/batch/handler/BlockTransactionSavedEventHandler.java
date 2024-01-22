package org.xangle.xpilot.batch.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.xangle.xpilot.batch.event.BlockTransactionSavedEvent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
public class BlockTransactionSavedEventHandler {

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(final BlockTransactionSavedEvent blockTransactionSavedEvent) {
        String executeTime = formatTime(blockTransactionSavedEvent.executionTime());

        log.info("Batch executed at {}: Saved {} blocks and {} transactions.",
                executeTime, blockTransactionSavedEvent.blockSize(), blockTransactionSavedEvent.transactionSize());
    }

    private String formatTime(LocalDateTime time) {
        return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
