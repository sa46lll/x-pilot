package org.xangle.xpilot.core.service.block;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.xangle.xpilot.core.entity.BlockEntity;
import org.xangle.xpilot.core.exception.CustomErrorType;
import org.xangle.xpilot.core.exception.ErrorTypeException;
import org.xangle.xpilot.core.model.request.BlockListRequest;
import org.xangle.xpilot.core.model.response.BlockListInfo;
import org.xangle.xpilot.core.model.response.PageableInfo;
import org.xangle.xpilot.core.repository.block.MongoBlockRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlockService {

    private final MongoBlockRepository mongoBlockRepository;

    public PageableInfo<BlockListInfo> findAll(BlockListRequest blockListRequest) {
        Pageable pageable = PageRequest.of(
                blockListRequest.page(), blockListRequest.size(), Sort.sort(BlockEntity.class).by(BlockEntity::getNumber).descending());

        Page<BlockEntity> page = mongoBlockRepository.findAll(pageable);

        List<BlockListInfo> blocks = page.getContent().stream()
                .map(BlockListInfo::from)
                .toList();

        return PageableInfo.of(
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                blocks
        );
    }

    public BlockEntity findByNumber(Long number) {
        return mongoBlockRepository.findByNumber(number)
                .orElseThrow(() -> new ErrorTypeException("해당 블록이 존재하지 않습니다.", CustomErrorType.BLOCK_NOT_FOUND));
    }
}
