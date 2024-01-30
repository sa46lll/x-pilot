package org.xangle.xpilot.core.facade.comment;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.xangle.xpilot.core.model.request.CommentRequest;
import org.xangle.xpilot.core.model.response.CommentInfo;

@SpringBootTest
class CommentFacadeServiceTest {

    @Autowired
    private CommentFacadeService commentFacadeService;

    @Test
    @Disabled
    void 더미데이터_댓글_100만개를_생성한다() {
        CommentInfo comment = commentFacadeService.save(483L,
                new CommentRequest("65b91a0e442d43488b2129c5", "comment"));

        for (int i = 0; i < 1_000_000; i++) {
            CommentInfo reply = commentFacadeService.save(483L,
                    new CommentRequest(comment.id(), "comment"));

            comment = reply;
        }
    }
}