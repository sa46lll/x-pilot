package org.xangle.xpilot.core.facade.block;

import lombok.RequiredArgsConstructor;
import org.xangle.xpilot.core.aspect.annotation.Facade;
import org.xangle.xpilot.core.entity.BlockEntity;
import org.xangle.xpilot.core.entity.TransactionEntity;
import org.xangle.xpilot.core.model.request.BlockDetailRequest;
import org.xangle.xpilot.core.model.request.BlockListRequest;
import org.xangle.xpilot.core.model.response.BlockDetailInfo;
import org.xangle.xpilot.core.model.response.BlockDetailInfo2;
import org.xangle.xpilot.core.model.response.BlockListInfo;
import org.xangle.xpilot.core.model.response.CommentChildInfo;
import org.xangle.xpilot.core.model.response.CommentDetailInfo;
import org.xangle.xpilot.core.model.response.PageableInfo;
import org.xangle.xpilot.core.model.response.TransactionInfo;
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

    public PageableInfo<BlockListInfo> findAll(BlockListRequest blockListRequest) {
        return blockService.findAll(blockListRequest);
    }
    
    public BlockDetailInfo findByBlockNumber(BlockDetailRequest blockDetailRequest) {
        BlockEntity block = blockService.findByNumber(blockDetailRequest.blockNumber()); // BlockInfo
        List<TransactionEntity> transactions = transactionService.findAllByBlockNumber(block.getNumber());
        PageableInfo<CommentChildInfo> comments = commentService.findAllByBlockNumber(block.getNumber(), blockDetailRequest);

        List<TransactionInfo> transactionInfo = transactions.stream()
                .map(TransactionInfo::from)
                .toList();

        return BlockDetailInfo.of(block, transactionInfo, comments);
    }

    public BlockDetailInfo2 findSummaryByBlockNumber(BlockDetailRequest blockDetailRequest) {
        BlockEntity block = blockService.findByNumber(blockDetailRequest.blockNumber());
        List<TransactionEntity> transactions = transactionService.findAllByBlockNumber(block.getNumber());
        PageableInfo<CommentDetailInfo> comments = commentService.findRootCommentsByBlockNumber(blockDetailRequest);

        List<TransactionInfo> transactionInfo = transactions.stream()
                .map(TransactionInfo::from)
                .toList();

        return BlockDetailInfo2.of(block, transactionInfo, comments);
    }
}
