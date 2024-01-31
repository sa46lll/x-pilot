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
import org.xangle.xpilot.core.service.comment.CommentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/block")
public class CommentController {

    private final CommentService commentService;
    private final CommentFacadeService commentFacadeService;

    @PostMapping("/{blockNumber}/comment")
    public CommentInfo save(@PathVariable Long blockNumber,
                            @RequestBody @Valid CommentRequest commentRequest) {
        return commentFacadeService.save(blockNumber, commentRequest);
    }

    @PatchMapping("/{blockNumber}/comment/{commentId}")
    public void update(@PathVariable Long blockNumber,
                       @PathVariable String commentId,
                       @RequestBody @Valid CommentUpdateRequest commentUpdateRequest) {
        commentFacadeService.update(blockNumber, commentId, commentUpdateRequest);
    }

    @DeleteMapping("/{blockNumber}/comment/{commentId}")
    public void delete(@PathVariable Long blockNumber,
                       @PathVariable String commentId) {
        commentFacadeService.delete(blockNumber, commentId);
    }

    /**
     * 대댓글 조회
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
        return commentService.findAllByParentId(
                new ReplyListRequest(blockNumber, commentId, page, size));
    }
}
