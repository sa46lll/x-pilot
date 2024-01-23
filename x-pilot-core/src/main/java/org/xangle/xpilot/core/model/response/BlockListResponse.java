package org.xangle.xpilot.core.model.response;

import org.xangle.xpilot.core.entity.BlockEntity;

import java.time.Duration;
import java.time.LocalDateTime;

public record BlockListResponse(
        Long blockNumber,
        long ageInSeconds,
        int txCount,
        String miner
) {
    public static BlockListResponse from(BlockEntity blockEntity) {
        return new BlockListResponse(
                blockEntity.getNumber(),
                Duration.between(blockEntity.getTime(), LocalDateTime.now()).getSeconds(),
                blockEntity.getTransactionCount(),
                blockEntity.getMiner()
        );
    }
}
