package org.xangle.xpilot.core.model.response;

import java.util.List;

public record BlockDetailInfo(
        Long blockNumber,
        String age,
        int trxCount,
        String miner,
        List<TransactionInfo> transactions,
        PageableInfo<CommentChildInfo> comments
) {

}
