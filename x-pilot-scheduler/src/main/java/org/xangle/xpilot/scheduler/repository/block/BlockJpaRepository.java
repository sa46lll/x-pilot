package org.xangle.xpilot.scheduler.repository.block;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xangle.xpilot.scheduler.entity.block.BlockJpaEntity;

public interface BlockJpaRepository extends JpaRepository<BlockJpaEntity, Long>, CustomBlockJpaRepository {
}
