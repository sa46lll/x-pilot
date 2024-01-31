package org.xangle.xpilot.scheduler.model.event;

import org.xangle.xpilot.scheduler.entity.block.BlockJpaEntity;

import java.time.LocalDateTime;
import java.util.List;

public record BlockTransactionSavedEvent(
        LocalDateTime executionTime,
        int blockSize,
        Long minBlockNumber,
        Long maxBlockNumber,
        int transactionSize
) {

    public static BlockTransactionSavedEvent of(List<BlockJpaEntity> blocks, int trxCount) {
        return new BlockTransactionSavedEvent(
                LocalDateTime.now(),
                blocks.size(),
                blocks.get(0).getNumber(),
                blocks.get(blocks.size() - 1).getNumber(),
                trxCount
        );
    }
}