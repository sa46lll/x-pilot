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
import org.xangle.xpilot.core.model.response.CommentChildInfo;
import org.xangle.xpilot.core.model.response.PageableInfo;
import org.xangle.xpilot.core.repository.comment.MongoCommentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final MongoCommentRepository mongoCommentRepository;

    public CommentEntity findById(String commentId) {
        return mongoCommentRepository.findById(commentId)
                .orElseThrow(() -> new ErrorTypeException("Comment not found", CustomErrorType.COMMENT_NOT_FOUND));
    }

    public void addComment(CommentSaveDto commentSaveDto) {
        mongoCommentRepository.save(
                CommentEntityMapper.toEntity(
                        commentSaveDto.workerId(),
                        commentSaveDto.blockNumber(),
                        "",
                        "",
                        0L,
                        1L,
                        commentSaveDto.content())
        );
    }

    public void addReply(ReplySaveDto replySaveDto) {
        CommentEntity parent = findById(replySaveDto.parentId());
        String rootId = parent.getRootId().isBlank() ? parent.getId() : parent.getRootId();

        mongoCommentRepository.save(
                CommentEntityMapper.toEntity(
                        replySaveDto.workerId(),
                        replySaveDto.blockNumber(),
                        rootId,
                        replySaveDto.parentId(),
                        parent.getDepth() + 1,
                        parent.getSequence() + 1,
                        replySaveDto.content())
        );
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
        List<CommentEntity> replies = mongoCommentRepository.findAllByRootIdIn(
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
}
