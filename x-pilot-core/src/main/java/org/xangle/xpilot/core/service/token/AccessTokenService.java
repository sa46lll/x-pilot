package org.xangle.xpilot.core.service.token;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xangle.xpilot.core.entity.AccessTokenEntity;
import org.xangle.xpilot.core.entity.TokenBlackListEntity;
import org.xangle.xpilot.core.repository.auth.MongoAccessTokenRepository;
import org.xangle.xpilot.core.repository.auth.MongoTokenBlacklistRepository;
import org.xangle.xpilot.core.service.DateUtilService;

@Service
@RequiredArgsConstructor
public class AccessTokenService {

    private final MongoAccessTokenRepository mongoAccessTokenRepository;
    private final MongoTokenBlacklistRepository mongoTokenBlacklistRepository;

    public void save(AccessTokenEntity accessTokenEntity) {
        mongoAccessTokenRepository.save(accessTokenEntity);
    }

    @Transactional
    public void expire(String accessToken) {
        TokenBlackListEntity tokenBlackListEntity = new TokenBlackListEntity(accessToken);
        tokenBlackListEntity.expire();

        mongoTokenBlacklistRepository.save(tokenBlackListEntity);
    }

    public boolean exists(String accessToken) {
        return mongoAccessTokenRepository.existsByAccessTokenAndExpiredTimeBefore(accessToken, DateUtilService.now());
    }

    public boolean isExpired(String accessToken) {
        return mongoTokenBlacklistRepository.existsTokenBlackListEntityByToken(accessToken);
    }
}
