package org.xangle.xpilot.core.model.response;

import org.xangle.xpilot.core.entity.BlockEntity;
import org.xangle.xpilot.core.service.DateUtilService;

import java.util.List;

public record BlockDetailInfo(
        Long blockNumber,
        String age,
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
                DateUtilService.getAge(block.getTime()),
                block.getTransactionCount(),
                block.getMiner(),
                transactions,
                comments
        );
    }
}
