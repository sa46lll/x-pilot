package org.xangle.xpilot.batch.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.xangle.xpilot.batch.facade.block.BlockFacadeService;

@Component
@RequiredArgsConstructor
public class BlockTransactionMigrationScheduler {

    private final BlockFacadeService blockFacadeService;

    @Scheduled(cron = "0/1 * * * * *")
    public void runBlockTransactionMigrationJob() {
        blockFacadeService.save();
    }
}
