package org.xangle.xpilot.core.controller.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xangle.xpilot.core.facade.comment.CommentFacadeService;
import org.xangle.xpilot.core.model.request.CommentRequest;
import org.xangle.xpilot.core.model.request.CommentUpdateRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/block")
public class CommentController {

    private final CommentFacadeService commentFacadeService;

    @PostMapping("/{blockNumber}/comment")
    public void save(@PathVariable Long blockNumber,
                     @RequestBody CommentRequest commentRequest) {
        commentFacadeService.save(blockNumber, commentRequest);
    }

    @PatchMapping("/{blockNumber}/comment/{commentId}")
    public void update(@PathVariable Long blockNumber,
                       @PathVariable String commentId,
                       @RequestBody CommentUpdateRequest commentUpdateRequest) {
        commentFacadeService.update(blockNumber, commentId, commentUpdateRequest);
    }

    @DeleteMapping("/{blockNumber}/comment/{commentId}")
    public void delete(@PathVariable Long blockNumber,
                       @PathVariable String commentId) {
        commentFacadeService.delete(blockNumber, commentId);
    }
}
