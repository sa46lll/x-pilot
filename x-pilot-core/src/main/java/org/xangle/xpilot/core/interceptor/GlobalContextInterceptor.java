package org.xangle.xpilot.core.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.xangle.xpilot.core.jwt.TokenProvider;
import org.xangle.xpilot.core.model.ContextHandler;

@Component
@RequiredArgsConstructor
public class GlobalContextInterceptor implements HandlerInterceptor {

    private static final String BEARER = "Bearer ";

    private final TokenProvider tokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (bearerToken != null && bearerToken.startsWith(BEARER)) {
            String workerId = tokenProvider.getWorkerId(bearerToken.substring(7));
            ContextHandler.setWorkerId(workerId);
            return true;
        }
        return false;
    }
}
