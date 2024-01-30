package org.xangle.xpilot.core.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.xangle.xpilot.core.service.DateUtilService;

import java.time.Instant;

@Getter
@Document("token_blacklist")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenBlackListEntity {

    @Value("${jwt.access-token-validity-in-seconds}")
    private static long accessTokenValidityInSeconds;

    @MongoId
    private String token;

    @Field("expired_time")
    private Instant expiredTime;

    public TokenBlackListEntity(String token) {
        this.token = token;
    }

    public void expire() {
        this.expiredTime = DateUtilService.now();
    }
}
