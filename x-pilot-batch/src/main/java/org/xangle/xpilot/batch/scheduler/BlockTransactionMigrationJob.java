package org.xangle.xpilot.batch.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.xangle.xpilot.batch.facade.BlockTransactionFacade;

@Component
@RequiredArgsConstructor
public class BlockTransactionMigrationJob {

    private final BlockTransactionFacade blockTransactionFacade;

    public void run() {
        blockTransactionFacade.save();
    }
}
