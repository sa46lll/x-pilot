package org.xangle.xpilot.core.service.token;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xangle.xpilot.core.entity.AccessTokenEntity;
import org.xangle.xpilot.core.entity.TokenBlackListEntity;
import org.xangle.xpilot.core.jwt.TokenProvider;
import org.xangle.xpilot.core.model.request.LoginRequest;
import org.xangle.xpilot.core.repository.auth.MongoAccessTokenRepository;
import org.xangle.xpilot.core.repository.auth.MongoTokenBlacklistRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AccessTokenService {

    private final TokenProvider tokenProvider;
    private final MongoAccessTokenRepository mongoAccessTokenRepository;
    private final MongoTokenBlacklistRepository mongoTokenBlacklistRepository;

    public void save(final AccessTokenEntity accessTokenEntity) {
        mongoAccessTokenRepository.save(accessTokenEntity);
    }

    @Transactional
    public void expire(String accessToken) {
        mongoTokenBlacklistRepository.save(TokenBlackListEntity.createAccessToken(accessToken));
    }

    public boolean isExpired(String accessToken) {
        return mongoTokenBlacklistRepository.existsTokenBlackListEntityByToken(accessToken);
    }

    public boolean exists(String accessToken) {
        return mongoAccessTokenRepository.existsByAccessTokenAndExpiredTimeBefore(accessToken, LocalDateTime.now());
    }

    public String create(final String email, final String workerId) {
        return tokenProvider.createToken(email, workerId);
    }
}
