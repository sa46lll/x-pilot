package org.xangle.xpilot.core.facade.comment;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.xangle.xpilot.core.model.request.CommentDummyRequest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CommentFacadeServiceTest {

    @Autowired
    private CommentFacadeService commentFacadeService;

    @Test
    @Disabled
    void 더미데이터_댓글_100만개를_생성한다() {
        Long blockNumber = 483L;
        String rootId = "65b985603fcef24b7dc049ac";
        long parentIdx = 535703L;
        Long depth = 706011L;
        List<CommentDummyRequest> requests = new ArrayList<>();

        for (int i = 0; i < 1_000_000; i++) {
            String objectId = getObjectId(rootId, parentIdx + 1);
            String parentId = getObjectId(rootId, parentIdx);
            requests.add(new CommentDummyRequest(
                    objectId,
                    rootId,
                    parentId,
                    depth,
                    1L,
                    "comment",
                    "65b0dcb4bf79af2e03f97f95"
            ));
            parentIdx++;
            depth++;
        }

        commentFacadeService.saveAllDummy(blockNumber, requests);
    }

    private static String getObjectId(String rootId, long idx) {
        String objectId = rootId + idx;
        if (objectId.length() > 24) {
            objectId = objectId.substring(objectId.length() - 24);
        }
        return objectId;
    }
}