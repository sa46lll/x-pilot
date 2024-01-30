package org.xangle.xpilot.scheduler.facade.block;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.xangle.xpilot.scheduler.entity.block.BlockJpaEntity;
import org.xangle.xpilot.scheduler.entity.transaction.TransactionJpaEntity;
import org.xangle.xpilot.scheduler.model.event.BlockTransactionSavedEvent;
import org.xangle.xpilot.scheduler.service.block.BlockService;
import org.xangle.xpilot.scheduler.service.transaction.TransactionService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BlockFacadeService {

    private static final int BLOCKS_PER_FETCH = 100;

    private final BlockService blockService;
    private final TransactionService transactionService;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    public void migrate() {
        Long lastBlockNumber = blockService.findLastBlockNumber();

        List<BlockJpaEntity> blocks = blockService.findAllByNumberRange(lastBlockNumber, BLOCKS_PER_FETCH);
        List<TransactionJpaEntity> trxs = transactionService.findAllByBlockNumberRange(
                lastBlockNumber, lastBlockNumber + BLOCKS_PER_FETCH);

        blockService.saveAll(blocks);
        transactionService.saveAll(trxs);

        applicationEventPublisher.publishEvent(
                BlockTransactionSavedEvent.from(blocks, trxs.size()));
    }
}
