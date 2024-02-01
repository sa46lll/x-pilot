package org.xangle.xpilot.core.model.response;

public record BlockListInfo(
        Long blockNumber,
        String age,
        long trxCount,
        String miner
) {

}
