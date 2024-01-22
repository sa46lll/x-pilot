package org.xangle.xpilot.batch.repository.block;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xangle.xpilot.batch.entity.block.BlockJpaEntity;

import java.util.List;

public interface BlockJpaRepository extends JpaRepository<BlockJpaEntity, Long> {

    List<BlockJpaEntity> findAllByNumberAfter(Long number);
}
