package org.xangle.xpilot.core.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("access_token")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccessTokenEntity {

    private static final int EXPIRED_TIME_IN_MINUTES = 5;

    @Id
    private String accessToken;
    private String email;
    private LocalDateTime expiredTime;

    public AccessTokenEntity(String accessToken, String email, LocalDateTime expiredTime) {
        this.accessToken = accessToken;
        this.email = email;
        this.expiredTime = expiredTime;
    }

    public static AccessTokenEntity of(String accessToken, String email) {
        return new AccessTokenEntity(accessToken, email, createExpiredTime());
    }

    public static LocalDateTime createExpiredTime() {
        return LocalDateTime.now().plusMinutes(EXPIRED_TIME_IN_MINUTES);
    }
}
