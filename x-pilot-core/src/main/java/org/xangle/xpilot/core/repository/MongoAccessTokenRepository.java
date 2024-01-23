package org.xangle.xpilot.core.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.xangle.xpilot.core.entity.AccessTokenEntity;

import java.time.LocalDateTime;

public interface MongoAccessTokenRepository extends MongoRepository<AccessTokenEntity, String> {

    boolean existsByAccessTokenAndExpiredTimeBefore(String accessToken, LocalDateTime currentTime);
}
