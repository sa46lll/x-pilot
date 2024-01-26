package org.xangle.xpilot.core.model.response;

import org.xangle.xpilot.core.entity.BlockEntity;

import java.time.Duration;
import java.time.LocalDateTime;

public record BlockListInfo(
        Long blockNumber,
        long ageInSeconds,
        int txCount,
        String miner
) {
    public static BlockListInfo from(BlockEntity blockEntity) {
        return new BlockListInfo(
                blockEntity.getNumber(),
                Duration.between(blockEntity.getTime(), LocalDateTime.now()).getSeconds(),
                blockEntity.getTransactionCount(),
                blockEntity.getMiner()
        );
    }
}
