package org.xangle.xpilot.core.service.token;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.xangle.xpilot.core.entity.AccessTokenEntity;
import org.xangle.xpilot.core.repository.MongoAccessTokenRepository;

@Service
@RequiredArgsConstructor
public class AccessTokenService {

    private final MongoAccessTokenRepository mongoAccessTokenRepository;

    public void save(final AccessTokenEntity accessTokenEntity) {
        mongoAccessTokenRepository.save(accessTokenEntity);
    }
}
