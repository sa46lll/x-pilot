package org.xangle.xpilot.scheduler.repository.block;

import org.xangle.xpilot.scheduler.entity.block.BlockJpaEntity;

import java.util.List;

public interface CustomBlockJpaRepository {

    List<BlockJpaEntity> findAllByNumberAfter(Long number, int count);
}
