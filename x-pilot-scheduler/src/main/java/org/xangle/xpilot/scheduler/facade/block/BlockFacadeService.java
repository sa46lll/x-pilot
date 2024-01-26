package org.xangle.xpilot.scheduler.facade.block;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.xangle.xpilot.scheduler.entity.block.BlockJpaEntity;
import org.xangle.xpilot.scheduler.model.event.BlockTransactionSavedEvent;
import org.xangle.xpilot.scheduler.service.block.BlockService;
import org.xangle.xpilot.scheduler.service.transaction.TransactionService;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BlockFacadeService {

    private final BlockService blockService;
    private final TransactionService transactionService;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    public void migrate() {
        Long lastBlockNumber = blockService.findLastBlockNumber();
        List<BlockJpaEntity> blocks = blockService.findAllAfterBlockNumber(lastBlockNumber);

        blocks.forEach(block -> {
            blockService.save(block);
            block.getTransactions().forEach(transactionService::save);
        });

        applicationEventPublisher.publishEvent(
                new BlockTransactionSavedEvent(LocalDateTime.now(), blocks.size()));
    }
}
