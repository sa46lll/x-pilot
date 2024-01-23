package org.xangle.xpilot.core.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.xangle.xpilot.core.exception.CustomErrorType;
import org.xangle.xpilot.core.exception.ErrorTypeException;

import java.io.IOException;

/**
 * 사용자가 인증되지 않은 상태에서 보호된 자원에 액세스하려고 할 때 401 UnAuthorized 에러 리턴
 */
@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        log.info("인증 정보가 유효하지 않습니다.");
        response.setContentType("application/json;charset=UTF-8");
        String result = objectMapper.writeValueAsString(
                new ErrorTypeException("인증 정보가 유효하지 않습니다.", CustomErrorType.UNAUTHORIZED));
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, result);
        response.getWriter().write(result);
    }
}