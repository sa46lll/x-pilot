package org.xangle.xpilot.core.controller.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xangle.xpilot.core.facade.auth.AuthFacadeService;
import org.xangle.xpilot.core.model.request.LoginRequest;
import org.xangle.xpilot.core.model.request.SignupRequest;
import org.xangle.xpilot.core.model.response.AccessTokenInfo;
import org.xangle.xpilot.core.model.response.SignupInfo;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthController {

    private final AuthFacadeService authFacadeService;

    /**
     * 회원가입
     *
     * @param signupRequest
     * @return SignupInfo
     */
    @PostMapping("/signup")
    public SignupInfo signup(@RequestBody @Valid SignupRequest signupRequest) {
        return authFacadeService.signup(signupRequest);
    }

    /**
     * 로그인
     *
     * @param loginRequest
     * @return AccessTokenInfo
     */
    @PostMapping("/login")
    public AccessTokenInfo login(@RequestBody @Valid LoginRequest loginRequest) {
        return authFacadeService.login(loginRequest);
    }

    /**
     * 로그아웃
     *
     * @param bearerToken
     */
    @PostMapping("/logout")
    public void logout(@RequestHeader(HttpHeaders.AUTHORIZATION) String bearerToken) {
        authFacadeService.expire(bearerToken.substring(7));
    }
}
