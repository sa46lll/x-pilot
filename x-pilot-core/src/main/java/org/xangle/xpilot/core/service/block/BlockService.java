package org.xangle.xpilot.core.service.block;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.xangle.xpilot.core.entity.BlockEntity;
import org.xangle.xpilot.core.entity.TransactionEntity;
import org.xangle.xpilot.core.exception.CustomErrorType;
import org.xangle.xpilot.core.exception.ErrorTypeException;
import org.xangle.xpilot.core.model.request.BlockListRequest;
import org.xangle.xpilot.core.model.response.BlockListInfo;
import org.xangle.xpilot.core.model.response.PageableInfo;
import org.xangle.xpilot.core.repository.block.MongoBlockRepository;
import org.xangle.xpilot.core.repository.transaction.MongoTransactionRepository;
import org.xangle.xpilot.core.service.DateUtilService;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Service
@RequiredArgsConstructor
public class BlockService {

    private final MongoBlockRepository mongoBlockRepository;
    private final MongoTransactionRepository mongoTransactionRepository;

    public PageableInfo<BlockListInfo> getAll(BlockListRequest blockListRequest) {
        Pageable pageable = PageRequest.of(
                blockListRequest.page(), blockListRequest.size(), Sort.sort(BlockEntity.class).by(BlockEntity::getNumber).descending());

        Page<BlockEntity> page = mongoBlockRepository.findAll(pageable);
        List<BlockEntity> blocks = page.getContent();

        Long maxBlockNumber = blocks.get(0).getNumber();
        Long minBlockNumber = blocks.get(blocks.size() - 1).getNumber();

        List<TransactionEntity> trxs = mongoTransactionRepository.findAllByBlockNumberBetween(minBlockNumber, maxBlockNumber);

        Map<Long, Long> trxCountMap = trxs.stream()
                .collect(groupingBy(TransactionEntity::getBlockNumber, counting()));

        List<BlockListInfo> response = blocks.stream()
                .map(block -> new BlockListInfo(
                        block.getNumber(),
                        DateUtilService.getAge(block.getTime()),
                        trxCountMap.getOrDefault(block.getNumber(), 0L),
                        block.getMiner()))
                .toList();

        return PageableInfo.of(
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                response
        );
    }

    public BlockEntity getByNumber(Long number) {
        return mongoBlockRepository.findByNumber(number)
                .orElseThrow(() -> new ErrorTypeException("해당 블록이 존재하지 않습니다.", CustomErrorType.BLOCK_NOT_FOUND));
    }
}
