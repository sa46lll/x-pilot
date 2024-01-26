package org.xangle.xpilot.scheduler.mapper;

import org.xangle.xpilot.scheduler.service.ByteConverterService;
import org.xangle.xpilot.scheduler.entity.block.BlockJpaEntity;
import org.xangle.xpilot.scheduler.entity.block.BlockMongoEntity;

public class BlockMongoEntityMapper {

    private BlockMongoEntityMapper() {
    }

    public static BlockMongoEntity toMongoEntity(BlockJpaEntity blockJpaEntity) {
        return new BlockMongoEntity(
                blockJpaEntity.getNumber(),
                blockJpaEntity.getTime(),
                ByteConverterService.convertToString(blockJpaEntity.getHash()),
                ByteConverterService.convertToString(blockJpaEntity.getParentHash()),
                ByteConverterService.convertToString(blockJpaEntity.getMiner()),
                blockJpaEntity.getSize(),
                blockJpaEntity.getTransactions().size()
        );
    }
}
