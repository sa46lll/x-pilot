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
import org.xangle.xpilot.core.service.worker.WorkerService;
import org.xangle.xpilot.core.service.token.AccessTokenService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthController {

    private final WorkerService workerService;
    private final AuthFacadeService authFacadeService;
    private final AccessTokenService accessTokenService;

    @PostMapping("/signup")
    public SignupInfo signup(@RequestBody @Valid SignupRequest signupRequest) {
        return workerService.signup(signupRequest);
    }

    @PostMapping("/login")
    public AccessTokenInfo login(@RequestBody @Valid LoginRequest loginRequest) {
        return authFacadeService.login(loginRequest);
    }

    @PostMapping("/logout")
    public void logout(@RequestHeader(HttpHeaders.AUTHORIZATION) String bearerToken) {
        accessTokenService.expire(bearerToken.substring(7));
    }
}
