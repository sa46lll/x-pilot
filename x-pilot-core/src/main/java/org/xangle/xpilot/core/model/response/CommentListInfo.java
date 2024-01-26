package org.xangle.xpilot.core.model.response;

import org.xangle.xpilot.core.entity.CommentEntity;

import java.util.List;

public record CommentListInfo(
        CommentDetailInfo comment,
        List<CommentDetailInfo> replies

) {

    public static CommentListInfo of(CommentEntity comment, List<CommentEntity> replies) {
        return new CommentListInfo(
                CommentDetailInfo.of(comment),
                replies.stream()
                        .map(CommentDetailInfo::of).toList()
        );
    }
}
