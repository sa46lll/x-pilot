package org.xangle.xpilot.core.controller.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xangle.xpilot.core.facade.comment.CommentFacadeService;
import org.xangle.xpilot.core.jwt.XPilotWorker;
import org.xangle.xpilot.core.model.CommentInfo;
import org.xangle.xpilot.core.model.request.CommentUpdateInfo;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/block")
public class CommentController {

    private final CommentFacadeService commentFacadeService;

    @PostMapping("/{blockNumber}/comment")
    public void save(@AuthenticationPrincipal XPilotWorker xPilotWorker,
                     @PathVariable final Long blockNumber,
                     @RequestBody final CommentInfo commentInfo) {
        commentFacadeService.save(
                xPilotWorker.getId(), blockNumber, commentInfo);
    }

    @PatchMapping("/{blockNumber}/comment/{commentId}")
    public void update(@AuthenticationPrincipal XPilotWorker xPilotWorker,
                       @PathVariable final Long blockNumber,
                       @PathVariable final String commentId,
                       @RequestBody final CommentUpdateInfo commentUpdateInfo) {
        commentFacadeService.update(
                xPilotWorker.getId(), blockNumber, commentId, commentUpdateInfo);
    }
}
