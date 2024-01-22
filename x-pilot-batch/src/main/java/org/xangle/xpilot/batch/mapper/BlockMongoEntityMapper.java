package org.xangle.xpilot.batch.mapper;

import org.xangle.xpilot.batch.entity.block.BlockJpaEntity;
import org.xangle.xpilot.batch.entity.block.BlockMongoEntity;

public class BlockMongoEntityMapper {

    private BlockMongoEntityMapper() {
    }

    public static BlockMongoEntity toMongoEntity(BlockJpaEntity blockJpaEntity) {
        return new BlockMongoEntity(
                blockJpaEntity.getNumber(),
                blockJpaEntity.getTime(),
                blockJpaEntity.getHash(),
                blockJpaEntity.getParentHash(),
                blockJpaEntity.getMiner(),
                blockJpaEntity.getSize()
        );
    }
}
