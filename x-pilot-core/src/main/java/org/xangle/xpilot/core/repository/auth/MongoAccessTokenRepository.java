package org.xangle.xpilot.core.repository.auth;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.xangle.xpilot.core.entity.AccessTokenEntity;

import java.time.Instant;

public interface MongoAccessTokenRepository extends MongoRepository<AccessTokenEntity, String> {

    boolean existsByAccessTokenAndExpiredTimeBefore(String accessToken, Instant currentTime);
}
