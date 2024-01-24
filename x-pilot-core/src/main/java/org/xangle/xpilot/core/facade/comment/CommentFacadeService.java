package org.xangle.xpilot.core.facade.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.xangle.xpilot.core.aspect.annotation.Facade;
import org.xangle.xpilot.core.entity.BlockEntity;
import org.xangle.xpilot.core.entity.CommentEntity;
import org.xangle.xpilot.core.mapper.comment.CommentEntityMapper;
import org.xangle.xpilot.core.model.CommentInfo;
import org.xangle.xpilot.core.model.CommentSaveDto;
import org.xangle.xpilot.core.model.ReplySaveDto;
import org.xangle.xpilot.core.service.block.BlockService;
import org.xangle.xpilot.core.service.comment.CommentService;

@Facade
@RequiredArgsConstructor
public class CommentFacadeService {

    private final BlockService blockService;
    private final CommentService commentService;

    @Transactional
    public void save(final String workerId, final Long blockNumber, final CommentInfo commentInfo) {
        BlockEntity block = blockService.findByNumber(blockNumber);
        boolean isRoot = commentInfo.parentId().isBlank();

        if (isRoot) {
            commentService.addComment(
                    CommentSaveDto.of(block.getNumber(), workerId, commentInfo.content()));
            return;
        }
        commentService.addReply(
                ReplySaveDto.of(block.getNumber(), workerId, commentInfo.parentId(), commentInfo.content()));
    }
}
