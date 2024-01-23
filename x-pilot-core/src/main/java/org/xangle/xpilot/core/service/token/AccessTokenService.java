package org.xangle.xpilot.core.service.token;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xangle.xpilot.core.entity.AccessTokenEntity;
import org.xangle.xpilot.core.entity.TokenBlackListEntity;
import org.xangle.xpilot.core.repository.MongoAccessTokenRepository;
import org.xangle.xpilot.core.repository.token.MongoTokenBlacklistRepository;

@Service
@RequiredArgsConstructor
public class AccessTokenService {

    private final MongoAccessTokenRepository mongoAccessTokenRepository;
    private final MongoTokenBlacklistRepository mongoTokenBlacklistRepository;

    public void save(final AccessTokenEntity accessTokenEntity) {
        mongoAccessTokenRepository.save(accessTokenEntity);
    }

    @Transactional
    public void expire(String accessToken) {
        mongoTokenBlacklistRepository.save(TokenBlackListEntity.createAccessToken(accessToken));
    }
}
