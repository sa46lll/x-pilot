package org.xangle.xpilot.batch.facade;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.xangle.xpilot.batch.entity.block.BlockJpaEntity;
import org.xangle.xpilot.batch.event.BlockTransactionSavedEvent;
import org.xangle.xpilot.batch.service.block.BlockService;
import org.xangle.xpilot.batch.service.transaction.TransactionService;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BlockTransactionFacade {

    private final BlockService blockService;
    private final TransactionService transactionService;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    public void save() {
        Long lastBlockNumber = blockService.findLastBlock()
                .map(BlockJpaEntity::getNumber)
                .orElse(0L);

        List<BlockJpaEntity> blocks = blockService.findAllAfterBlockNumber(lastBlockNumber);

        blocks.forEach(block -> {
            blockService.save(block);
            block.getTransactions().forEach(transactionService::save);
        });

        applicationEventPublisher.publishEvent(
                new BlockTransactionSavedEvent(LocalDateTime.now(), blocks.size(), 0));
    }
}
