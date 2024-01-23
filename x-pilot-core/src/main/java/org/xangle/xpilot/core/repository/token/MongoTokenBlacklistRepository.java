package org.xangle.xpilot.core.repository.token;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.xangle.xpilot.core.entity.TokenBlackListEntity;

public interface MongoTokenBlacklistRepository extends MongoRepository<TokenBlackListEntity, String> {
}
