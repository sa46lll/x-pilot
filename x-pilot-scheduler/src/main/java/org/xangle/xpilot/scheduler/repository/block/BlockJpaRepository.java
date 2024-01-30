package org.xangle.xpilot.scheduler.repository.block;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xangle.xpilot.scheduler.entity.block.BlockJpaEntity;

import java.util.List;

public interface BlockJpaRepository extends JpaRepository<BlockJpaEntity, Long> {

    List<BlockJpaEntity> findAllByNumberBetween(Long startNumber, Long endNumber);
}
