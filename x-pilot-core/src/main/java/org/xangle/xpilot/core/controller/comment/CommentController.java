package org.xangle.xpilot.core.controller.comment;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xangle.xpilot.core.facade.comment.CommentFacadeService;
import org.xangle.xpilot.core.model.request.CommentRequest;
import org.xangle.xpilot.core.model.request.CommentUpdateRequest;
import org.xangle.xpilot.core.model.request.ReplyListRequest;
import org.xangle.xpilot.core.model.response.CommentInfo;
import org.xangle.xpilot.core.model.response.PageableInfo;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/block")
public class CommentController {

    private final CommentFacadeService commentFacadeService;

    /**
     * 댓글 생성
     *
     * @param blockNumber
     * @param commentRequest
     * @return CommentInfo
     */
    @PostMapping("/{blockNumber}/comment")
    public CommentInfo save(@PathVariable Long blockNumber,
                            @RequestBody @Valid CommentRequest commentRequest) {
        return commentFacadeService.save(blockNumber, commentRequest);
    }

    /**
     * 댓글 수정
     *
     * @param blockNumber
     * @param commentId
     * @param commentUpdateRequest
     * @return CommentInfo
     */
    @PatchMapping("/{blockNumber}/comment/{commentId}")
    public CommentInfo update(@PathVariable Long blockNumber,
                              @PathVariable String commentId,
                              @RequestBody @Valid CommentUpdateRequest commentUpdateRequest) {
        return commentFacadeService.update(blockNumber, commentId, commentUpdateRequest);
    }

    /**
     * 댓글 삭제
     *
     * @param blockNumber
     * @param commentId
     * @return CommentInfo
     */
    @DeleteMapping("/{blockNumber}/comment/{commentId}")
    public CommentInfo delete(@PathVariable Long blockNumber,
                              @PathVariable String commentId) {
        return commentFacadeService.delete(blockNumber, commentId);
    }

    /**
     * 대댓글 목록 조회
     *
     * @param blockNumber
     * @param commentId
     * @param page
     * @param size
     * @return PageableInfo<CommentInfo>
     */
    @GetMapping("/{blockNumber}/comment/{commentId}/reply")
    public PageableInfo<CommentInfo> getReplies(@PathVariable Long blockNumber,
                                                @PathVariable String commentId,
                                                @RequestParam(required = false, defaultValue = "1") int page,
                                                @RequestParam(required = false, defaultValue = "5") int size) {
        return commentFacadeService.findAllByParentId(
                new ReplyListRequest(blockNumber, commentId, page, size));
    }
}
