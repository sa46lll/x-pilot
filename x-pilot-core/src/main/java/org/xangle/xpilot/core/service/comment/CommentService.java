package org.xangle.xpilot.core.service.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xangle.xpilot.core.entity.CommentEntity;
import org.xangle.xpilot.core.exception.CustomErrorType;
import org.xangle.xpilot.core.exception.ErrorTypeException;
import org.xangle.xpilot.core.mapper.comment.CommentEntityMapper;
import org.xangle.xpilot.core.model.CommentSaveDto;
import org.xangle.xpilot.core.model.ReplySaveDto;
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

    public CommentEntity addComment(CommentSaveDto commentSaveDto) {
        CommentEntity comment = CommentEntityMapper.toEntity(
                commentSaveDto.workerId(),
                commentSaveDto.blockNumber(),
                "",
                "",
                0L,
                1L,
                commentSaveDto.content());
        mongoCommentRepository.save(comment);

        return comment;
    }

    public CommentEntity addReply(ReplySaveDto replySaveDto) {
        CommentEntity parent = findById(replySaveDto.parentId());
        Long lastSequence = mongoCommentRepository.findFirstByParentIdOrderBySequenceDesc(replySaveDto.parentId())
                .map(CommentEntity::getSequence)
                .orElse(0L);

        String rootId = parent.getRootId().isBlank() ? parent.getId() : parent.getRootId();

        CommentEntity reply = CommentEntityMapper.toEntity(
                replySaveDto.workerId(),
                replySaveDto.blockNumber(),
                rootId,
                replySaveDto.parentId(),
                parent.getDepth() + 1L,
                lastSequence + 1L,
                replySaveDto.content());

        mongoCommentRepository.save(reply);

        return reply;
    }

    @Transactional
    public void update(CommentEntity comment, String content) {
        comment.updateContent(content);
        mongoCommentRepository.save(comment);
    }

    public void delete(CommentEntity comment) {
        comment.delete();
        mongoCommentRepository.save(comment);
    }

    public PageableInfo<CommentChildInfo> findAllByBlockNumber(Long blockNumber, BlockDetailRequest blockDetailRequest) {
        Pageable pageable = PageRequest.of(blockDetailRequest.page(), blockDetailRequest.size());
        Page<CommentEntity> comments = mongoCommentRepository.findAllByBlockNumberAndDepth(blockNumber, 0L, pageable);
        List<CommentEntity> replies = mongoCommentRepository.findAllByRootIdIn( // between
                comments.stream()
                        .map(CommentEntity::getId)
                        .toList());

        List<CommentChildInfo> response = new ArrayList<>();

        for (CommentEntity root : comments) {
            Map<String, CommentChildInfo> map = replies.stream()
                    .filter(commentEntity -> commentEntity.getRootId().equals(root.getId()))
                    .collect(Collectors.toMap(
                            commentEntity -> commentEntity.getParentId() + "_" + commentEntity.getSequence(),
                            CommentChildInfo::of
                    ));

            CommentChildInfo comment = CommentChildInfo.of(root);
            comment.setReplies(map);
            response.add(comment);
        }

        return PageableInfo.of(
                comments.getNumber(),
                comments.getSize(),
                comments.getTotalPages(),
                comments.getTotalElements(),
                response);
    }

    public PageableInfo<CommentDetailInfo> findRootCommentsByBlockNumber(BlockDetailRequest blockDetailRequest) {
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

    public PageableInfo<CommentInfo> findAllByParentId(ReplyListRequest replyListRequest) {
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
