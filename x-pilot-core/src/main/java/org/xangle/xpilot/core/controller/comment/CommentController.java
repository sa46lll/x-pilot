package org.xangle.xpilot.core.controller.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xangle.xpilot.core.exception.CustomErrorType;
import org.xangle.xpilot.core.exception.ErrorTypeException;
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
                     @PathVariable Long blockNumber,
                     @RequestBody CommentInfo commentInfo) {
        commentFacadeService.save(xPilotWorker.getWorkerId(), blockNumber, commentInfo);
    }

    @PatchMapping("/{blockNumber}/comment/{commentId}")
    public void update(@AuthenticationPrincipal XPilotWorker xPilotWorker,
                       @PathVariable Long blockNumber,
                       @PathVariable String commentId,
                       @RequestBody CommentUpdateInfo commentUpdateInfo) {
        commentFacadeService.update(
                xPilotWorker.getWorkerId(), blockNumber, commentId, commentUpdateInfo);
    }

    @DeleteMapping("/{blockNumber}/comment/{commentId}")
    public void delete(@AuthenticationPrincipal XPilotWorker xPilotWorker,
                       @PathVariable Long blockNumber,
                       @PathVariable String commentId) {
        commentFacadeService.delete(
                xPilotWorker.getWorkerId(), blockNumber, commentId);
    }

    @GetMapping("/test")
    public String test() {
        throw new ErrorTypeException("test", CustomErrorType.SERVER_ERROR);
    }
}

// interceptor, threadlocal
