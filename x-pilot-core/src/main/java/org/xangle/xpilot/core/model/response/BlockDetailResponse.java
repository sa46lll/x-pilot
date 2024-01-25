package org.xangle.xpilot.core.model.response;

import org.xangle.xpilot.core.entity.BlockEntity;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public record BlockDetailResponse(
        Long blockNumber,
        long ageInSeconds,
        int txCount,
        String miner,
        List<TransactionResponse> transactions,
        GlobalPageResponse<CommentResponse> comments
) {

    public static BlockDetailResponse of(BlockEntity block,
                                         List<TransactionResponse> transactions,
                                         GlobalPageResponse<CommentResponse> comments) {
        return new BlockDetailResponse(
                block.getNumber(),
                Duration.between(block.getTime(), LocalDateTime.now()).getSeconds(),
                block.getTransactionCount(),
                block.getMiner(),
                transactions,
                comments
        );
    }
}
