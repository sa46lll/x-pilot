package org.xangle.xpilot.core.facade.block;

import lombok.RequiredArgsConstructor;
import org.xangle.xpilot.core.aspect.annotation.Facade;
import org.xangle.xpilot.core.entity.BlockEntity;
import org.xangle.xpilot.core.entity.TransactionEntity;
import org.xangle.xpilot.core.model.request.BlockDetailRequest;
import org.xangle.xpilot.core.model.request.BlockListRequest;
import org.xangle.xpilot.core.model.response.BlockDetailInfo;
import org.xangle.xpilot.core.model.response.BlockSummaryDetailInfo;
import org.xangle.xpilot.core.model.response.BlockListInfo;
import org.xangle.xpilot.core.model.response.CommentChildInfo;
import org.xangle.xpilot.core.model.response.CommentDetailInfo;
import org.xangle.xpilot.core.model.response.PageableInfo;
import org.xangle.xpilot.core.model.response.TransactionInfo;
import org.xangle.xpilot.core.service.DateUtilService;
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

    public PageableInfo<BlockListInfo> getAll(BlockListRequest blockListRequest) {
        return blockService.getAll(blockListRequest);
    }

    public BlockDetailInfo getByBlockNumber(BlockDetailRequest blockDetailRequest) {
        BlockEntity block = blockService.getByNumber(blockDetailRequest.blockNumber());
        List<TransactionEntity> transactions = transactionService.getAllByBlockNumber(block.getNumber());
        PageableInfo<CommentChildInfo> comments = commentService.getAllByBlockNumber(block.getNumber(), blockDetailRequest);

        List<TransactionInfo> transactionInfo = transactions.stream()
                .map(TransactionInfo::from)
                .toList();

        return new BlockDetailInfo(
                block.getNumber(),
                DateUtilService.getAge(block.getTime()),
                transactionInfo.size(),
                block.getMiner(),
                transactionInfo,
                comments
        );
    }

    public BlockSummaryDetailInfo getSummaryByBlockNumber(BlockDetailRequest blockDetailRequest) {
        BlockEntity block = blockService.getByNumber(blockDetailRequest.blockNumber());
        List<TransactionEntity> transactions = transactionService.getAllByBlockNumber(block.getNumber());
        PageableInfo<CommentDetailInfo> comments = commentService.getRootCommentsByBlockNumber(blockDetailRequest);

        List<TransactionInfo> transactionInfo = transactions.stream()
                .map(TransactionInfo::from)
                .toList();

        return new BlockSummaryDetailInfo(
                block.getNumber(),
                DateUtilService.getAge(block.getTime()),
                transactionInfo.size(),
                block.getMiner(),
                transactionInfo,
                comments
        );
    }
}
