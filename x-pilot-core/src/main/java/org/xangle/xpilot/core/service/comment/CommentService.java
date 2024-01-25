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
import org.xangle.xpilot.core.model.request.BlockDetailInfo;
import org.xangle.xpilot.core.model.response.CommentListResponse;
import org.xangle.xpilot.core.model.response.GlobalPageResponse;
import org.xangle.xpilot.core.repository.comment.MongoCommentRepository;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

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

    public GlobalPageResponse<CommentListResponse> findAllByBlockNumber(Long blockNumber, BlockDetailInfo blockDetailInfo) {
        Pageable pageable = PageRequest.of(blockDetailInfo.page(), blockDetailInfo.size());
        Page<CommentEntity> roots = mongoCommentRepository.findAllByBlockNumberAndDepth(blockNumber, 0L, pageable);

        List<String> rootIds = roots.stream()
                .map(CommentEntity::getId)
                .toList();

        List<CommentEntity> replies = mongoCommentRepository.findAllByRootIdIn(rootIds);

        Map<String, List<CommentEntity>> groupRootId = replies.stream()
                .collect(groupingBy(CommentEntity::getRootId));

        for (CommentEntity rootEntity : roots) {
            if (!groupRootId.containsKey(rootEntity.getId())) {
                groupRootId.put(rootEntity.getId(), List.of());
            }
        }

        Map<CommentEntity, List<CommentEntity>> groupByRootComment = groupRootId.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> findCommentEntityByRootId(roots.getContent(), entry.getKey()),
                        Entry::getValue
                ));

        List<CommentListResponse> response = groupByRootComment.entrySet().stream()
                .map(entry -> CommentListResponse.of(entry.getKey(), entry.getValue()))
                .toList();

        return GlobalPageResponse.of(
                roots.getNumber(),
                roots.getSize(),
                roots.getTotalPages(),
                roots.getTotalElements(),
                response);
    }

    private CommentEntity findCommentEntityByRootId(List<CommentEntity> commentEntities, String rootId) {
        return commentEntities.stream()
                .filter(commentEntity -> commentEntity.getId().equals(rootId))
                .findAny()
                .orElseThrow(() -> new ErrorTypeException("Comment not found", CustomErrorType.COMMENT_NOT_FOUND));
    }
}
