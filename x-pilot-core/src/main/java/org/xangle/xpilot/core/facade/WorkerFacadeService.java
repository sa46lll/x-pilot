package org.xangle.xpilot.core.facade;

import lombok.RequiredArgsConstructor;
import org.xangle.xpilot.core.aspect.annotation.Facade;
import org.xangle.xpilot.core.entity.AccessTokenEntity;
import org.xangle.xpilot.core.entity.WorkerEntity;
import org.xangle.xpilot.core.jwt.TokenProvider;
import org.xangle.xpilot.core.model.request.LoginRequest;
import org.xangle.xpilot.core.model.response.TokenResponse;
import org.xangle.xpilot.core.service.worker.WorkerService;
import org.xangle.xpilot.core.service.token.AccessTokenService;

@Facade
@RequiredArgsConstructor
public class WorkerFacadeService {

    private final WorkerService workerService;
    private final AccessTokenService accessTokenService;
    private final TokenProvider tokenProvider;

    public TokenResponse login(final LoginRequest loginRequest) {
        WorkerEntity worker = workerService.findByEmailAndPassword(loginRequest.email(), loginRequest.password());
        String accessToken = tokenProvider.createToken(loginRequest.email(), worker.getId().toHexString());

        accessTokenService.save(
                AccessTokenEntity.of(accessToken));

        return TokenResponse.of(loginRequest.email(), accessToken);
    }
}
