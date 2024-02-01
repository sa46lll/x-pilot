package org.xangle.xpilot.core.model.response;

import org.xangle.xpilot.core.entity.BlockEntity;
import org.xangle.xpilot.core.service.DateUtilService;

import java.util.List;

public record BlockDetailInfo(
        Long blockNumber,
        String age,
        int trxCount,
        String miner,
        List<TransactionInfo> transactions,
        PageableInfo<CommentChildInfo> comments
) {

    public static BlockDetailInfo of(BlockEntity block,
                                     List<TransactionInfo> transactions,
                                     PageableInfo<CommentChildInfo> comments) {
        return new BlockDetailInfo(
                block.getNumber(),
                DateUtilService.getAge(block.getTime()),
                transactions.size(),
                block.getMiner(),
                transactions,
                comments
        );
    }
}
