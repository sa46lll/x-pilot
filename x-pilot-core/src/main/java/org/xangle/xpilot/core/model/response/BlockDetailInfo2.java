package org.xangle.xpilot.core.model.response;

import org.xangle.xpilot.core.entity.BlockEntity;
import org.xangle.xpilot.core.service.DateUtilService;

import java.util.List;

public record BlockDetailInfo2(
        Long blockNumber,
        String age,
        int txCount,
        String miner,
        List<TransactionInfo> transactions,
        PageableInfo<CommentDetailInfo> comments
) {

    public static BlockDetailInfo2 of(BlockEntity block,
                                      List<TransactionInfo> transactions,
                                      PageableInfo<CommentDetailInfo> comments) {
        return new BlockDetailInfo2(
                block.getNumber(),
                DateUtilService.getAge(block.getTime()),
                transactions.size(),
                block.getMiner(),
                transactions,
                comments
        );
    }
}
