package org.xangle.xpilot.batch.service.block;

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

    public Long findLastBlockNumber() {
        return blockMongoRepository.findTopByOrderByNumberDesc()
                .map(BlockMongoEntity::getNumber)
                .orElse(-1L);
    }

    public List<BlockJpaEntity> findAllAfterBlockNumber(Long blockNumber) {
        return blockJpaRepository.findAllByNumberAfter(blockNumber);
    }

    public void save(BlockJpaEntity block) {
        blockMongoRepository.save(BlockMongoEntityMapper.toMongoEntity(block));
    }
}
