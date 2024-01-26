package org.xangle.xpilot.core.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.xangle.xpilot.core.service.DateUtilService;

import java.time.Instant;

@Document("access_token")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccessTokenEntity {

    @Value("${jwt.access-token-validity-in-seconds}")
    private static long accessTokenValidityInSeconds;

    @MongoId
    private String accessToken;

    @Field("expired_time")
    private Instant expiredTime;

    public AccessTokenEntity(String accessToken, Instant expiredTime) {
        this.accessToken = accessToken;
        this.expiredTime = expiredTime;
    }

    public static AccessTokenEntity from(String accessToken) {
        return new AccessTokenEntity(accessToken, createExpiredTime());
    }

    public static Instant createExpiredTime() {
        return DateUtilService.now().plusSeconds(accessTokenValidityInSeconds);
    }
}
