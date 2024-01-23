package org.xangle.xpilot.core.service.block;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.xangle.xpilot.core.entity.BlockEntity;
import org.xangle.xpilot.core.model.request.BlockListInfo;
import org.xangle.xpilot.core.model.response.BlockListResponse;
import org.xangle.xpilot.core.model.response.GlobalPageResponse;
import org.xangle.xpilot.core.repository.block.MongoBlockRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlockService {

    private final MongoBlockRepository mongoBlockRepository;

    public GlobalPageResponse<BlockListResponse> findAll(final BlockListInfo blockListInfo) {
        Pageable pageable = PageRequest.of(blockListInfo.page(), blockListInfo.size(), Sort.unsorted());

        Page<BlockEntity> page = mongoBlockRepository.findAll(pageable);

        List<BlockListResponse> blocks = page.getContent().stream()
                .map(BlockListResponse::from)
                .toList();

        return GlobalPageResponse.of(
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                blocks
        );
    }
}
