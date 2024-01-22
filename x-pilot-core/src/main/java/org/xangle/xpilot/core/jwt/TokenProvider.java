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
import org.xangle.xpilot.core.model.response.TokenResponse;

import java.security.Key;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class TokenProvider {

    private final String secret;
    private final long tokenValidityInMilliseconds;
    private Key key;

    public TokenProvider(@Value("${jwt.secret}") String secret,
                         @Value("${jwt.token-validity-in-seconds}") long tokenValidityInSeconds) {
        this.secret = secret;
        this.tokenValidityInMilliseconds = tokenValidityInSeconds * 1000;
    }

    @PostConstruct
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public TokenResponse createToken(Long memberId, String email) {
        long now = (new Date()).getTime();

        Date tokenExpiresIn = new Date(now + this.tokenValidityInMilliseconds);
        String accessToken = Jwts.builder()
                .setSubject(email)
                .claim("memberId", memberId)
                .setExpiration(tokenExpiresIn)
                .signWith(this.key, SignatureAlgorithm.HS512)
                .compact();

        return new TokenResponse(memberId, accessToken);
    }

    public Authentication getAuthentication(String accessToken) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(accessToken)
                .getBody();
        Integer memberId = (Integer) claims.get("memberId");

        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
        XPilotMember principal = new XPilotMember(Long.valueOf(memberId), authorities);
        return new UsernamePasswordAuthenticationToken(principal, accessToken, authorities);
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
        if (!validateToken(accessToken)) {
            return false;
        }
        return true; // blacklist check, accessToken check
    }
}
