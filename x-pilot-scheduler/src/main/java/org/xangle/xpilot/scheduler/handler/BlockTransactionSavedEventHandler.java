package org.xangle.xpilot.scheduler.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.xangle.xpilot.scheduler.model.event.BlockTransactionSavedEvent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
public class BlockTransactionSavedEventHandler {

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(BlockTransactionSavedEvent event) {
        String executeTime = formatTime(event.executionTime());

        log.info("Scheduler executed at {}: Saved {} blocks from block number {} to {} with {} transactions.",
                executeTime,
                event.blockSize(),
                event.minBlockNumber(),
                event.maxBlockNumber(),
                event.transactionSize());
    }

    private String formatTime(LocalDateTime time) {
        return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
