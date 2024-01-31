package org.xangle.xpilot.core.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.xangle.xpilot.core.model.XPilotWorker;
import org.xangle.xpilot.core.service.token.AccessTokenService;

import java.security.Key;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class TokenProvider {

    private static final String CLAIM_KEY_WORKER_ID = "workerId";

    private final String secret;
    private final long accessTokenValidityInMilliseconds;
    private final AccessTokenService accessTokenService;
    private Key key;

    public TokenProvider(@Value("${jwt.secret}") String secret,
                         @Value("${jwt.access-token-validity-in-seconds}") long accessTokenValidityInSeconds,
                         AccessTokenService accessTokenService) {
        this.secret = secret;
        this.accessTokenValidityInMilliseconds = accessTokenValidityInSeconds * 1000;
        this.accessTokenService = accessTokenService;
    }

    @PostConstruct
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String createToken(String email, String workerId) {
        long now = (new Date()).getTime();
        Date tokenExpiresIn = new Date(now + this.accessTokenValidityInMilliseconds);

        return Jwts.builder()
                .setSubject(email)
                .claim(CLAIM_KEY_WORKER_ID, workerId)
                .setExpiration(tokenExpiresIn)
                .signWith(this.key, SignatureAlgorithm.HS512)
                .compact();
    }

    public Authentication getAuthentication(String accessToken) {
        String workerId = getWorkerId(accessToken);
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));

        XPilotWorker principal = new XPilotWorker(workerId, authorities);
        return new UsernamePasswordAuthenticationToken(principal, accessToken, authorities);
    }

    public String getWorkerId(String accessToken) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(accessToken)
                .getBody();
        return (String) claims.get(CLAIM_KEY_WORKER_ID);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        }
        return false;
    }

    public boolean validateAccessToken(String accessToken) {
        return validateToken(accessToken) && accessTokenService.exists(accessToken) && !accessTokenService.isExpired(accessToken);
    }
}
