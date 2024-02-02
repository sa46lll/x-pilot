package org.xangle.xpilot.core.model.response;

import org.xangle.xpilot.core.entity.BlockEntity;
import org.xangle.xpilot.core.service.DateUtilService;

import java.util.List;

public record BlockSummaryDetailInfo(
        Long blockNumber,
        String age,
        int trxCount,
        String miner,
        List<TransactionInfo> transactions,
        PageableInfo<CommentDetailInfo> comments
) {

    public static BlockSummaryDetailInfo of(BlockEntity block,
                                            List<TransactionInfo> transactions,
                                            PageableInfo<CommentDetailInfo> comments) {
        return new BlockSummaryDetailInfo(
                block.getNumber(),
                DateUtilService.getAge(block.getTime()),
                transactions.size(),
                block.getMiner(),
                transactions,
                comments
        );
    }
}
