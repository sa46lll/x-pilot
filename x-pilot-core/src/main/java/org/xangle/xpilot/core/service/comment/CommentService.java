package org.xangle.xpilot.core.service.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.xangle.xpilot.core.entity.CommentEntity;
import org.xangle.xpilot.core.exception.CustomErrorType;
import org.xangle.xpilot.core.exception.ErrorTypeException;
import org.xangle.xpilot.core.model.CommentSaveDto;
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
}
