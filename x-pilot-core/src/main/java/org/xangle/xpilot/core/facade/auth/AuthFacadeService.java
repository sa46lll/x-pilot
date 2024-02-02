package org.xangle.xpilot.core.facade.auth;

import lombok.RequiredArgsConstructor;
import org.xangle.xpilot.core.aspect.annotation.Facade;
import org.xangle.xpilot.core.entity.AccessTokenEntity;
import org.xangle.xpilot.core.entity.WorkerEntity;
import org.xangle.xpilot.core.jwt.TokenProvider;
import org.xangle.xpilot.core.model.request.LoginRequest;
import org.xangle.xpilot.core.model.request.SignupRequest;
import org.xangle.xpilot.core.model.response.AccessTokenInfo;
import org.xangle.xpilot.core.model.response.SignupInfo;
import org.xangle.xpilot.core.service.worker.WorkerService;
import org.xangle.xpilot.core.service.token.AccessTokenService;

@Facade
@RequiredArgsConstructor
public class AuthFacadeService {

    private final TokenProvider tokenProvider;
    private final WorkerService workerService;
    private final AccessTokenService accessTokenService;

    public SignupInfo signup(SignupRequest signupRequest) {
        return workerService.signup(signupRequest);
    }

    public AccessTokenInfo login(LoginRequest loginRequest) {
        WorkerEntity worker = workerService.getByEmailAndPassword(loginRequest.email(), loginRequest.password());
        String accessToken = tokenProvider.createToken(loginRequest.email(), worker.getId());

        accessTokenService.save(
                AccessTokenEntity.from(accessToken));

        return AccessTokenInfo.of(worker.getId(), worker.getEmail(), accessToken);
    }

    public void expire(String token) {
        accessTokenService.expire(token);
    }
}
