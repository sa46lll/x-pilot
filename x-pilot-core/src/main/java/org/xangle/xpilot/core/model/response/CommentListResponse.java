package org.xangle.xpilot.core.model.response;

import org.xangle.xpilot.core.entity.CommentEntity;

import java.util.List;

public record CommentListResponse(
        CommentResponse comment,
        List<CommentResponse> replies

) {

    public static CommentListResponse of(CommentEntity comment, List<CommentEntity> replies) {
        return new CommentListResponse(
                CommentResponse.of(comment),
                replies.stream()
                        .map(CommentResponse::of).toList()
        );
    }
}
