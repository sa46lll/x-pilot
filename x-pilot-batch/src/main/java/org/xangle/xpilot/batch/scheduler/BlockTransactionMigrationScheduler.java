package org.xangle.xpilot.batch.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BlockTransactionMigrationScheduler {

    private final BlockTransactionMigrationJob blockTransactionMigrationJob;

    @Scheduled(cron = "0 0/1 * * * *") // every minute
    public void run() {
        blockTransactionMigrationJob.run();
    }
}
