package org.xangle.xpilot.core.model.response;

import org.xangle.xpilot.core.entity.BlockEntity;
import org.xangle.xpilot.core.service.DateUtilService;

public record BlockListInfo(
        Long blockNumber,
        String age,
        int txCount,
        String miner
) {
    public static BlockListInfo from(BlockEntity block) {
        return new BlockListInfo(
                block.getNumber(),
                DateUtilService.getAge(block.getTime()),
                0, // TODO: txCount
                block.getMiner()
        );
    }
}
