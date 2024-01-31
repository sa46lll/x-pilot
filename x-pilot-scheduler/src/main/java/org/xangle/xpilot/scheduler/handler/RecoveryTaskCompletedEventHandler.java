package org.xangle.xpilot.scheduler.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.xangle.xpilot.scheduler.model.event.RecoveryTaskCompletedEvent;

@Slf4j
@Component
public class RecoveryTaskCompletedEventHandler {

    @Async
    @EventListener
    public void handle(RecoveryTaskCompletedEvent event) {
        log.info("Recovery task completed at {}: {} tasks from block number {} to {}.",
                event.executionTime(),
                event.taskCount(),
                event.minBlockNumber(),
                event.maxBlockNumber());
    }
}
