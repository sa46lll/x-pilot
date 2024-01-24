package org.xangle.xpilot.core.facade.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.xangle.xpilot.core.aspect.annotation.Facade;
import org.xangle.xpilot.core.entity.BlockEntity;
import org.xangle.xpilot.core.entity.CommentEntity;
import org.xangle.xpilot.core.exception.CustomErrorType;
import org.xangle.xpilot.core.exception.ErrorTypeException;
import org.xangle.xpilot.core.model.CommentInfo;
import org.xangle.xpilot.core.model.CommentSaveDto;
import org.xangle.xpilot.core.model.ReplySaveDto;
import org.xangle.xpilot.core.model.request.CommentUpdateInfo;
import org.xangle.xpilot.core.service.block.BlockService;
import org.xangle.xpilot.core.service.comment.CommentService;

import java.util.Objects;

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

    @Transactional
    public void update(final String workerId, final Long blockNumber, final String commentId, final CommentUpdateInfo commentUpdateInfo) {
        CommentEntity comment = commentService.findById(commentId);
        validate(comment, workerId, blockNumber);

        commentService.update(comment, commentUpdateInfo.content());
    }

    @Transactional
    public void delete(String id, Long blockNumber, String commentId) {
        CommentEntity comment = commentService.findById(commentId);
        validate(comment, id, blockNumber);

        commentService.delete(comment);
    }

    private void validate(final CommentEntity comment, final String workerId, final Long blockNumber) {
        if (!Objects.equals(comment.getWorkerId(), workerId) || !Objects.equals(comment.getBlockNumber(), blockNumber)) {
            throw new ErrorTypeException("Comment not found", CustomErrorType.COMMENT_NOT_FOUND);
        }
    }
}
