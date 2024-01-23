package org.xangle.xpilot.core.repository.auth;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.xangle.xpilot.core.entity.TokenBlackListEntity;

public interface MongoTokenBlacklistRepository extends MongoRepository<TokenBlackListEntity, String> {
    boolean existsTokenBlackListEntityByToken(String accessToken);
}
