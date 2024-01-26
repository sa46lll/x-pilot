package org.xangle.xpilot.core.facade.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.xangle.xpilot.core.aspect.annotation.Facade;
import org.xangle.xpilot.core.entity.BlockEntity;
import org.xangle.xpilot.core.entity.CommentEntity;
import org.xangle.xpilot.core.exception.CustomErrorType;
import org.xangle.xpilot.core.exception.ErrorTypeException;
import org.xangle.xpilot.core.model.request.CommentRequest;
import org.xangle.xpilot.core.model.CommentSaveDto;
import org.xangle.xpilot.core.model.ReplySaveDto;
import org.xangle.xpilot.core.model.ContextHandler;
import org.xangle.xpilot.core.model.request.CommentUpdateRequest;
import org.xangle.xpilot.core.service.block.BlockService;
import org.xangle.xpilot.core.service.comment.CommentService;

import java.util.Objects;

@Facade
@RequiredArgsConstructor
public class CommentFacadeService {

    private final BlockService blockService;
    private final CommentService commentService;

    @Transactional
    public void save(Long blockNumber, CommentRequest commentRequest) {
        BlockEntity block = blockService.findByNumber(blockNumber);
        String workerId = ContextHandler.getWorkerId();
        boolean isRoot = commentRequest.parentId().isBlank();

        if (isRoot) {
            commentService.addComment(
                    CommentSaveDto.of(block.getNumber(), workerId, commentRequest.content()));
            return;
        }
        commentService.addReply(
                ReplySaveDto.of(block.getNumber(), workerId, commentRequest.parentId(), commentRequest.content()));
    }

    @Transactional
    public void update(Long blockNumber, String commentId, CommentUpdateRequest commentUpdateRequest) {
        CommentEntity comment = commentService.findById(commentId);
        validate(comment, ContextHandler.getWorkerId(), blockNumber);

        commentService.update(comment, commentUpdateRequest.content());
    }

    @Transactional
    public void delete(Long blockNumber, String commentId) {
        CommentEntity comment = commentService.findById(commentId);
        validate(comment, ContextHandler.getWorkerId(), blockNumber);

        commentService.delete(comment);
    }

    private void validate(CommentEntity comment, String workerId, Long blockNumber) {
        if (!Objects.equals(comment.getWorkerId(), workerId) || !Objects.equals(comment.getBlockNumber(), blockNumber)) {
            throw new ErrorTypeException("Comment not found", CustomErrorType.COMMENT_NOT_FOUND);
        }
    }
}
