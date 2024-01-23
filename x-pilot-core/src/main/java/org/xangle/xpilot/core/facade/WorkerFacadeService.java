package org.xangle.xpilot.core.facade;

import lombok.RequiredArgsConstructor;
import org.xangle.xpilot.core.aspect.annotation.Facade;
import org.xangle.xpilot.core.entity.AccessTokenEntity;
import org.xangle.xpilot.core.exception.CustomErrorType;
import org.xangle.xpilot.core.exception.ErrorTypeException;
import org.xangle.xpilot.core.jwt.TokenProvider;
import org.xangle.xpilot.core.model.request.LoginRequest;
import org.xangle.xpilot.core.model.response.TokenResponse;
import org.xangle.xpilot.core.service.WorkerService;
import org.xangle.xpilot.core.service.token.AccessTokenService;

@Facade
@RequiredArgsConstructor
public class WorkerFacadeService {

    private final WorkerService workerService;
    private final AccessTokenService accessTokenService;
    private final TokenProvider tokenProvider;

    public TokenResponse login(final LoginRequest loginRequest) {
        if (!workerService.existsByEmailAndPassword(loginRequest.email(), loginRequest.password())) {
            throw new ErrorTypeException("이메일 또는 비밀번호가 일치하지 않습니다.", CustomErrorType.SERVER_ERROR);
        }

        String accessToken = tokenProvider.createToken(loginRequest.email());
        accessTokenService.save(
                AccessTokenEntity.of(accessToken, loginRequest.email()));

        return TokenResponse.of(loginRequest.email(), accessToken);
    }
}
