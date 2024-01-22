package org.xangle.xpilot.batch.service.block;

import jdk.swing.interop.SwingInterOpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.xangle.xpilot.batch.entity.block.BlockJpaEntity;
import org.xangle.xpilot.batch.entity.block.BlockMongoEntity;
import org.xangle.xpilot.batch.mapper.BlockMongoEntityMapper;
import org.xangle.xpilot.batch.repository.block.BlockJpaRepository;
import org.xangle.xpilot.batch.repository.block.BlockMongoRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlockService {

    private final BlockJpaRepository blockJpaRepository;
    private final BlockMongoRepository blockMongoRepository;

    public Optional<BlockJpaEntity> findLastBlock() {
        return blockMongoRepository.findTopByOrderByNumberDesc();
    }

    public List<BlockJpaEntity> findAllAfterBlockNumber(Long blockNumber) {
        return blockJpaRepository.findAllByNumberAfter(blockNumber);
    }

    public void save(BlockJpaEntity block) {
        blockMongoRepository.save(BlockMongoEntityMapper.toMongoEntity(block));
    }
}
