package org.xangle.xpilot.core.model.response;

import org.xangle.xpilot.core.entity.BlockEntity;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public record BlockDetailInfo(
        Long blockNumber,
        long ageInSeconds,
        int txCount,
        String miner,
        List<TransactionInfo> transactions,
        PageableInfo<CommentListInfo> comments
) {

    public static BlockDetailInfo of(BlockEntity block,
                                     List<TransactionInfo> transactions,
                                     PageableInfo<CommentListInfo> comments) {
        return new BlockDetailInfo(
                block.getNumber(),
                Duration.between(block.getTime(), LocalDateTime.now()).getSeconds(),
                block.getTransactionCount(),
                block.getMiner(),
                transactions,
                comments
        );
    }
}
