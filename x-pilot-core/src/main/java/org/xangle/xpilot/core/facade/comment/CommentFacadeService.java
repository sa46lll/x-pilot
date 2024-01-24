package org.xangle.xpilot.core.facade.comment;

import lombok.RequiredArgsConstructor;
import org.xangle.xpilot.core.aspect.annotation.Facade;
import org.xangle.xpilot.core.entity.BlockEntity;
import org.xangle.xpilot.core.entity.CommentEntity;
import org.xangle.xpilot.core.mapper.comment.CommentEntityMapper;
import org.xangle.xpilot.core.model.CommentInfo;
import org.xangle.xpilot.core.service.block.BlockService;
import org.xangle.xpilot.core.service.comment.CommentService;

@Facade
@RequiredArgsConstructor
public class CommentFacadeService {

    private final BlockService blockService;
    private final CommentService commentService;

    public void save(final String workerId, final Long blockNumber, final CommentInfo commentInfo) {
        BlockEntity block = blockService.findByNumber(blockNumber);
        CommentEntity comment = commentService.findById(commentInfo.parentId());

        commentService.save(
                CommentEntityMapper.toEntity(workerId, block.getNumber(), comment.getRootId(), comment.getId(), comment.getDepth() + 1, commentInfo.content()));
    }
}
