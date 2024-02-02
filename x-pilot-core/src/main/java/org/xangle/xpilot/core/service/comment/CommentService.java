package org.xangle.xpilot.core.service.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.xangle.xpilot.core.entity.CommentEntity;
import org.xangle.xpilot.core.exception.CustomErrorType;
import org.xangle.xpilot.core.exception.ErrorTypeException;
import org.xangle.xpilot.core.mapper.comment.CommentEntityMapper;
import org.xangle.xpilot.core.model.request.CommentSaveRequest;
import org.xangle.xpilot.core.model.request.ReplySaveRequest;
import org.xangle.xpilot.core.model.request.BlockDetailRequest;
import org.xangle.xpilot.core.model.request.ReplyListRequest;
import org.xangle.xpilot.core.model.response.CommentChildInfo;
import org.xangle.xpilot.core.model.response.CommentDetailInfo;
import org.xangle.xpilot.core.model.response.CommentInfo;
import org.xangle.xpilot.core.model.response.PageableInfo;
import org.xangle.xpilot.core.repository.comment.CommentRepository;
import org.xangle.xpilot.core.repository.comment.MongoCommentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MongoCommentRepository mongoCommentRepository;

    public CommentEntity findById(String commentId) {
        return mongoCommentRepository.findById(commentId)
                .orElseThrow(() -> new ErrorTypeException("Comment not found", CustomErrorType.COMMENT_NOT_FOUND));
    }

    public CommentEntity addComment(CommentSaveRequest commentSaveRequest) {
        CommentEntity comment = CommentEntityMapper.toEntity(
                commentSaveRequest.workerId(),
                commentSaveRequest.blockNumber(),
                "",
                "",
                0L,
                1L,
                commentSaveRequest.content());
        mongoCommentRepository.save(comment);

        return comment;
    }

    public CommentEntity addReply(ReplySaveRequest replySaveRequest) {
        CommentEntity parent = findById(replySaveRequest.parentId());
        Long lastSequence = mongoCommentRepository.findFirstByParentIdOrderBySequenceDesc(replySaveRequest.parentId())
                .map(CommentEntity::getSequence)
                .orElse(0L);

        String rootId = parent.getRootId().isBlank() ? parent.getId() : parent.getRootId();

        CommentEntity reply = CommentEntityMapper.toEntity(
                replySaveRequest.workerId(),
                replySaveRequest.blockNumber(),
                rootId,
                replySaveRequest.parentId(),
                parent.getDepth() + 1L,
                lastSequence + 1L,
                replySaveRequest.content());

        mongoCommentRepository.save(reply);

        return reply;
    }

    public CommentEntity update(CommentEntity comment, String content) {
        comment.updateContent(content);
        mongoCommentRepository.save(comment);

        return comment;
    }

    public CommentEntity delete(CommentEntity comment) {
        comment.delete();
        mongoCommentRepository.save(comment);

        return comment;
    }

    public PageableInfo<CommentChildInfo> getAllByBlockNumber(Long blockNumber, BlockDetailRequest blockDetailRequest) {
        Pageable pageable = PageRequest.of(blockDetailRequest.page(), blockDetailRequest.size());
        Page<CommentEntity> comments = mongoCommentRepository.findAllByBlockNumberAndDepth(blockNumber, 0L, pageable);

        List<CommentChildInfo> response = new ArrayList<>();

        for (CommentEntity root : comments) {
            List<CommentEntity> replies = mongoCommentRepository.findAllByRootId(root.getId());

            Map<String, CommentChildInfo> repliesMap = replies.stream()
                    .collect(Collectors.toMap(
                            commentEntity -> commentEntity.getParentId() + "_" + commentEntity.getSequence(),
                            CommentChildInfo::of
                    ));

            CommentChildInfo comment = CommentChildInfo.of(root);
            comment.setReplies(repliesMap);
            response.add(comment);
        }

        return PageableInfo.of(
                comments.getNumber(),
                comments.getSize(),
                comments.getTotalPages(),
                comments.getTotalElements(),
                response);
    }

    public PageableInfo<CommentDetailInfo> getRootCommentsByBlockNumber(BlockDetailRequest blockDetailRequest) {
        Pageable pageable = PageRequest.of(blockDetailRequest.page(), blockDetailRequest.size());
        Page<CommentEntity> comments = mongoCommentRepository.findAllByBlockNumberAndDepth(blockDetailRequest.blockNumber(), 0L, pageable);

        return PageableInfo.of(
                comments.getNumber(),
                comments.getSize(),
                comments.getTotalPages(),
                comments.getTotalElements(),
                comments.stream()
                        .map(CommentDetailInfo::of)
                        .toList());
    }

    public PageableInfo<CommentInfo> getAllByParentId(ReplyListRequest replyListRequest) {
        Pageable pageable = PageRequest.of(replyListRequest.page(), replyListRequest.size());
        Page<CommentEntity> comments = mongoCommentRepository.findAllByParentId(replyListRequest.commentId(), pageable);

        return PageableInfo.of(
                comments.getNumber(),
                comments.getSize(),
                comments.getTotalPages(),
                comments.getTotalElements(),
                comments.stream()
                        .map(CommentInfo::from)
                        .toList());
    }

    // TODO: Remove this method (더미 데이터를 위한 메서드)
    public void addAllReplies(List<CommentEntity> comments) {
        commentRepository.bulkInsert(comments);
    }
}
