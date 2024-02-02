package org.xangle.xpilot.core.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.xangle.xpilot.core.entity.CommentEntity;
import org.xangle.xpilot.core.model.ContextHandler;

import java.time.Instant;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class CommentChildInfo {
    String commentId;
    String workerId;
    String content;
    Instant createTime;
    boolean canModify;
    boolean isDeleted;
    List<CommentChildInfo> replies;

    public static CommentChildInfo from(CommentEntity comment) {
        return new CommentChildInfo(
                comment.getId(),
                comment.getWorkerId(),
                comment.getContent(),
                comment.getCreatedTime(),
                comment.getWorkerId().equals(ContextHandler.getWorkerId()),
                comment.isDeleted(),
                new ArrayList<>()
        );
    }

    public void addReply(CommentChildInfo commentChildInfo) {
        replies.add(commentChildInfo);
    }

    public void setReplies(Map<String, CommentChildInfo> map) {
        Deque<CommentChildInfo> deque = new ArrayDeque<>();
        deque.add(this);

        while (!deque.isEmpty()) {
            CommentChildInfo comment = deque.pop();
            long sequence = 1L;

            while (true) {
                String key = comment.commentId + "_" + sequence;
                CommentChildInfo reply = map.get(key);

                if (reply == null) {
                    break;
                }

                comment.addReply(reply);
                deque.add(reply);
                sequence++;
            }
        }
    }
}
