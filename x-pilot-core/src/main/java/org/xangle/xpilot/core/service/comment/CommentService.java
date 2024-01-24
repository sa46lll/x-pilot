package org.xangle.xpilot.core.service.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xangle.xpilot.core.entity.CommentEntity;
import org.xangle.xpilot.core.exception.CustomErrorType;
import org.xangle.xpilot.core.exception.ErrorTypeException;
import org.xangle.xpilot.core.mapper.comment.CommentEntityMapper;
import org.xangle.xpilot.core.model.CommentSaveDto;
import org.xangle.xpilot.core.model.ReplySaveDto;
import org.xangle.xpilot.core.repository.comment.MongoCommentRepository;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final MongoCommentRepository mongoCommentRepository;

    public void save(final CommentEntity commentEntity) {
        mongoCommentRepository.save(commentEntity);
    }

    public CommentEntity findById(final String commentId) {
        return mongoCommentRepository.findById(commentId)
                .orElseThrow(() -> new ErrorTypeException("Comment not found", CustomErrorType.COMMENT_NOT_FOUND));
    }

    public void addComment(final CommentSaveDto commentSaveDto) {
        mongoCommentRepository.save(
                CommentEntityMapper.toEntity(
                        commentSaveDto.workerId(),
                        commentSaveDto.blockNumber(),
                        "",
                        "",
                        0L,
                        commentSaveDto.content())
        );
    }

    public void addReply(final ReplySaveDto replySaveDto) {
        CommentEntity parent = findById(replySaveDto.parentId());
        String rootId = parent.getRootId().isBlank() ? parent.getId() : parent.getRootId();

        mongoCommentRepository.save(
                CommentEntityMapper.toEntity(
                        replySaveDto.workerId(),
                        replySaveDto.blockNumber(),
                        rootId,
                        replySaveDto.parentId(),
                        parent.getDepth() + 1,
                        replySaveDto.content())
        );
    }

    @Transactional
    public void update(final CommentEntity comment, String content) {
        comment.updateContent(content);
        mongoCommentRepository.save(comment);
    }
}
