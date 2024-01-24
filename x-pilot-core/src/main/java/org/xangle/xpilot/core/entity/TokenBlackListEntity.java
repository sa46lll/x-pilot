package org.xangle.xpilot.core.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Getter
@Document("token_blacklist")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenBlackListEntity {

    @Value("${jwt.access-token-validity-in-seconds}")
    private static long accessTokenValidityInSeconds;

    @MongoId
    private String token;
    private LocalDateTime expiredTime;

    public TokenBlackListEntity(String token, LocalDateTime expiredTime) {
        this.token = token;
        this.expiredTime = expiredTime;
    }

    public static TokenBlackListEntity createAccessToken(String token) {
        return new TokenBlackListEntity(token, LocalDateTime.now().plusSeconds(accessTokenValidityInSeconds));
    }
}
