package org.xangle.xpilot.scheduler.facade.block;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.xangle.xpilot.scheduler.aspect.annotation.Facade;
import org.xangle.xpilot.scheduler.entity.block.BlockJpaEntity;
import org.xangle.xpilot.scheduler.entity.recovery.RecoveryTaskEntity;
import org.xangle.xpilot.scheduler.entity.transaction.TransactionJpaEntity;
import org.xangle.xpilot.scheduler.model.event.BlockTransactionSavedEvent;
import org.xangle.xpilot.scheduler.service.block.BlockService;
import org.xangle.xpilot.scheduler.service.recoverytask.RecoveryTaskService;
import org.xangle.xpilot.scheduler.service.transaction.TransactionService;

import java.util.List;

@Slf4j
@Facade
@RequiredArgsConstructor
public class BlockFacadeService {

    private static final int BLOCKS_PER_FETCH = 1_000;

    private final BlockService blockService;
    private final TransactionService transactionService;
    private final RecoveryTaskService recoveryTaskService;
    private final ApplicationEventPublisher applicationEventPublisher;

    public void migrate() { // graceful shutdown
        Long lastBlockNumber = blockService.findLastBlockNumber();
        Long lastBlockNumberByTrx = transactionService.findLastBlockNumber();

        List<BlockJpaEntity> blocks = blockService.findAllByNumberRange(lastBlockNumber, BLOCKS_PER_FETCH);
        List<TransactionJpaEntity> trxs = transactionService.findAllByBlockNumberRange(
                lastBlockNumberByTrx + 1, lastBlockNumber + BLOCKS_PER_FETCH);

        try {
            blockService.saveAll(blocks);
            if (!trxs.isEmpty()) {
                transactionService.saveAll(trxs);
            }

            applicationEventPublisher.publishEvent(
                    BlockTransactionSavedEvent.of(blocks, trxs.size()));

        } catch (Exception e) {
            log.error("Failed to migrate blocks and transactions", e);

            recoveryTaskService.save(
                    new RecoveryTaskEntity(lastBlockNumber + 1, lastBlockNumber + BLOCKS_PER_FETCH, false, e.getMessage()));
        }
    }
}
