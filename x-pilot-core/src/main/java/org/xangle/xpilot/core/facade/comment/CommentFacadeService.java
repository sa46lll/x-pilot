package org.xangle.xpilot.core.facade.comment;

import lombok.RequiredArgsConstructor;
import org.xangle.xpilot.core.aspect.annotation.Facade;
import org.xangle.xpilot.core.entity.BlockEntity;
import org.xangle.xpilot.core.entity.CommentEntity;
import org.xangle.xpilot.core.exception.CustomErrorType;
import org.xangle.xpilot.core.exception.ErrorTypeException;
import org.xangle.xpilot.core.model.request.CommentDummyRequest;
import org.xangle.xpilot.core.model.request.CommentRequest;
import org.xangle.xpilot.core.model.request.CommentSaveRequest;
import org.xangle.xpilot.core.model.request.ReplyListRequest;
import org.xangle.xpilot.core.model.request.ReplySaveRequest;
import org.xangle.xpilot.core.model.ContextHandler;
import org.xangle.xpilot.core.model.request.CommentUpdateRequest;
import org.xangle.xpilot.core.model.response.CommentInfo;
import org.xangle.xpilot.core.model.response.PageableInfo;
import org.xangle.xpilot.core.service.DateUtilService;
import org.xangle.xpilot.core.service.block.BlockService;
import org.xangle.xpilot.core.service.comment.CommentService;

import java.util.List;
import java.util.Objects;

@Facade
@RequiredArgsConstructor
public class CommentFacadeService {

    private final BlockService blockService;
    private final CommentService commentService;

    public CommentInfo save(Long blockNumber, CommentRequest commentRequest) {
        BlockEntity block = blockService.getByNumber(blockNumber);
        String workerId = ContextHandler.getWorkerId();
        boolean isRoot = commentRequest.parentId().isBlank();

        CommentEntity comment = isRoot ?
                commentService.addComment(new CommentSaveRequest(block.getNumber(),workerId, commentRequest.content())) :
                commentService.addReply(new ReplySaveRequest(block.getNumber(), workerId, commentRequest.parentId(), commentRequest.content()));

        return new CommentInfo(
                comment.getId(),
                comment.getRootId(),
                comment.getParentId(),
                comment.getDepth(),
                comment.getSequence(),
                comment.getContent(),
                comment.isDeleted(),
                comment.getCreatedTime(),
                comment.getUpdatedTime()
        );
    }

    public CommentInfo update(Long blockNumber, String commentId, CommentUpdateRequest commentUpdateRequest) {
        CommentEntity comment = commentService.findById(commentId);
        validate(comment, ContextHandler.getWorkerId(), blockNumber);

        CommentEntity updatedComment = commentService.update(comment, commentUpdateRequest.content());

        return new CommentInfo(
                updatedComment.getId(),
                updatedComment.getRootId(),
                updatedComment.getParentId(),
                updatedComment.getDepth(),
                updatedComment.getSequence(),
                updatedComment.getContent(),
                updatedComment.isDeleted(),
                updatedComment.getCreatedTime(),
                updatedComment.getUpdatedTime()
        );
    }

    public CommentInfo delete(Long blockNumber, String commentId) {
        CommentEntity comment = commentService.findById(commentId);
        validate(comment, ContextHandler.getWorkerId(), blockNumber);

        CommentEntity updatedComment = commentService.delete(comment);

        return new CommentInfo(
                updatedComment.getId(),
                updatedComment.getRootId(),
                updatedComment.getParentId(),
                updatedComment.getDepth(),
                updatedComment.getSequence(),
                updatedComment.getContent(),
                updatedComment.isDeleted(),
                updatedComment.getCreatedTime(),
                updatedComment.getUpdatedTime()
        );
    }

    private void validate(CommentEntity comment, String workerId, Long blockNumber) {
        if (!Objects.equals(comment.getWorkerId(), workerId) || !Objects.equals(comment.getBlockNumber(), blockNumber)) {
            throw new ErrorTypeException("Comment not found", CustomErrorType.COMMENT_NOT_FOUND);
        }
    }

    public PageableInfo<CommentInfo> getAllByParentId(ReplyListRequest replyListRequest) {
        return commentService.getAllByParentId(replyListRequest);
    }

    // TODO: Remove this method (더미 데이터를 위한 메서드)
    public void saveAllDummy(Long blockNumber, List<CommentDummyRequest> requests) {
        List<CommentEntity> comments = requests.stream().
                map(comment -> new CommentEntity(
                        comment.objectId(),
                        blockNumber,
                        comment.workerId(),
                        comment.rootId(),
                        comment.parentId(),
                        comment.depth(),
                        comment.sequence(),
                        comment.content(),
                        DateUtilService.now())
                ).toList();

        commentService.addAllReplies(comments);
    }
}
