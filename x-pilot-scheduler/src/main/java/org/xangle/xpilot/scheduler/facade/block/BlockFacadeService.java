package org.xangle.xpilot.scheduler.facade.block;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.xangle.xpilot.scheduler.entity.block.BlockJpaEntity;
import org.xangle.xpilot.scheduler.entity.transaction.TransactionJpaEntity;
import org.xangle.xpilot.scheduler.model.event.BlockTransactionSavedEvent;
import org.xangle.xpilot.scheduler.service.block.BlockService;
import org.xangle.xpilot.scheduler.service.transaction.TransactionService;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class BlockFacadeService {

    private static final int BLOCKS_PER_FETCH = 100_000;

    private final BlockService blockService;
    private final TransactionService transactionService;
    private final ApplicationEventPublisher applicationEventPublisher;

    public void migrate() {
        Long lastBlockNumber = blockService.findLastBlockNumber();

        List<BlockJpaEntity> blocks = blockService.findAllByNumberRange(lastBlockNumber, BLOCKS_PER_FETCH);
        List<TransactionJpaEntity> trxs = transactionService.findAllByBlockNumberRange(
                lastBlockNumber + 1, lastBlockNumber + BLOCKS_PER_FETCH);

        try {
            blockService.saveAll(blocks);
            if (!trxs.isEmpty()) {
                transactionService.saveAll(trxs);
            }

            applicationEventPublisher.publishEvent(
                    BlockTransactionSavedEvent.from(blocks, trxs.size()));

        } catch (Exception e) {
            log.error("Failed to migrate blocks and transactions", e);
        }
    }
}
