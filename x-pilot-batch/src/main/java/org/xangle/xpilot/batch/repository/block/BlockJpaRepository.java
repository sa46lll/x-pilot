package org.xangle.xpilot.batch.repository.block;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xangle.xpilot.batch.entity.block.BlockJpaEntity;

import java.util.List;
import java.util.Optional;

public interface BlockJpaRepository extends JpaRepository<BlockJpaEntity, String> {

    Optional<BlockJpaEntity> findTopByOrderByNumberDesc();

    List<BlockJpaEntity> findAllByNumberAfter(Long number);
}
