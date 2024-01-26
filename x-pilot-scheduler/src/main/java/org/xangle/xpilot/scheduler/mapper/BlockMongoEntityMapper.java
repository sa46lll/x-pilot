package org.xangle.xpilot.scheduler.mapper;

import org.xangle.xpilot.scheduler.converter.ByteConverter;
import org.xangle.xpilot.scheduler.entity.block.BlockJpaEntity;
import org.xangle.xpilot.scheduler.entity.block.BlockMongoEntity;

public class BlockMongoEntityMapper {

    private BlockMongoEntityMapper() {
    }

    public static BlockMongoEntity toMongoEntity(BlockJpaEntity blockJpaEntity) {
        return new BlockMongoEntity(
                blockJpaEntity.getNumber(),
                blockJpaEntity.getTime(),
                ByteConverter.convertToString(blockJpaEntity.getHash()),
                ByteConverter.convertToString(blockJpaEntity.getParentHash()),
                ByteConverter.convertToString(blockJpaEntity.getMiner()),
                blockJpaEntity.getSize(),
                blockJpaEntity.getTransactions().size()
        );
    }
}
