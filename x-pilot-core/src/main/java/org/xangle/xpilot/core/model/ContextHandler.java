package org.xangle.xpilot.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ContextHandler {

    private static final ThreadLocal<ContextHandler> WORKER_INFO = new InheritableThreadLocal<>();

    private String workerId;

    public static void setWorkerId(String workerId) {
        WORKER_INFO.set(new ContextHandler(workerId));
    }

    public static String getWorkerId() {
        return WORKER_INFO.get().workerId;
    }
}
