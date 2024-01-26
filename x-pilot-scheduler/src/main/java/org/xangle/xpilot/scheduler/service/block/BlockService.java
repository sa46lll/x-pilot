package org.xangle.xpilot.scheduler.service.block;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xangle.xpilot.scheduler.entity.block.BlockJpaEntity;
import org.xangle.xpilot.scheduler.entity.block.BlockMongoEntity;
import org.xangle.xpilot.scheduler.repository.block.BlockJpaRepository;
import org.xangle.xpilot.scheduler.repository.block.BlockMongoRepository;
import org.xangle.xpilot.scheduler.service.ByteConverterService;

import java.util.List;

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

    @Transactional(readOnly = true)
    public List<BlockJpaEntity> findAllAfterBlockNumber(Long blockNumber) {
        return blockJpaRepository.findAllByNumberAfter(blockNumber);
    }

    @Transactional
    public void save(BlockJpaEntity block) {
        blockMongoRepository.save(
                new BlockMongoEntity(
                        block.getNumber(),
                        block.getTime(),
                        ByteConverterService.convertToString(block.getHash()),
                        ByteConverterService.convertToString(block.getParentHash()),
                        ByteConverterService.convertToString(block.getMiner()),
                        block.getSize(),
                        block.getTransactions().size()
                ));
    }
}
