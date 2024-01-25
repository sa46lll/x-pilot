package org.xangle.xpilot.core.facade.block;

import lombok.RequiredArgsConstructor;
import org.xangle.xpilot.core.aspect.annotation.Facade;
import org.xangle.xpilot.core.entity.BlockEntity;
import org.xangle.xpilot.core.entity.TransactionEntity;
import org.xangle.xpilot.core.model.request.BlockDetailInfo;
import org.xangle.xpilot.core.model.response.BlockDetailResponse;
import org.xangle.xpilot.core.model.response.CommentResponse;
import org.xangle.xpilot.core.model.response.GlobalPageResponse;
import org.xangle.xpilot.core.model.response.TransactionResponse;
import org.xangle.xpilot.core.service.block.BlockService;
import org.xangle.xpilot.core.service.comment.CommentService;
import org.xangle.xpilot.core.service.transaction.TransactionService;

import java.util.List;

@Facade
@RequiredArgsConstructor
public class BlockFacadeService {

    private final BlockService blockService;
    private final CommentService commentService;
    private final TransactionService transactionService;

    public BlockDetailResponse findByBlockNumber(Long blockNumber, BlockDetailInfo blockDetailInfo) {
        BlockEntity block = blockService.findByNumber(blockNumber);
        List<TransactionEntity> transactions = transactionService.findAllByBlockNumber(blockNumber);
        GlobalPageResponse<CommentResponse> comments = commentService.findAllByBlockNumber(blockNumber, blockDetailInfo);

        List<TransactionResponse> transactionResponse = transactions.stream()
                .map(TransactionResponse::from)
                .toList();

        return BlockDetailResponse.of(block, transactionResponse, comments);
    }
}
