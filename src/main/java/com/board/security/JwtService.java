package com.board.security;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtService {
    private static final Long VALIDITY_TIME = 60 * 60 * 12 * 1000L;
    private final JwtUtil jwtUtil = new JwtUtil();

    public String createToken(String username) {
        return jwtUtil.createJwt(username, VALIDITY_TIME);
    }

    public String getSubject(String token) {
        return jwtUtil.getSubject(token);
    }

    public boolean isInvalid(String token) {
        try {
            jwtUtil.validate(token);
            return false;
        } catch (Exception e) {
            log.info("invalidToken: {}", e.getMessage());
            return true;
        }
    }

    private static class JwtUtil {
        private final JwtParser jwtParser;
        private final JwtBuilder jwtBuilder;

        public JwtUtil() {
            SecretKey secretKey = Jwts.SIG.HS256.key().build();
            jwtParser = Jwts.parser().verifyWith(secretKey).build();
            jwtBuilder = Jwts.builder().signWith(secretKey);
        }

        public String createJwt(String username, Long validTimeMillis) {
            return jwtBuilder
                    .subject(username)
                    .issuedAt(new Date())
                    .expiration(new Date(System.currentTimeMillis() + validTimeMillis))
                    .compact();
        }

        public void validate(String token) {
            jwtParser.parseSignedClaims(token);
        }

        public String getSubject(String token) {
            return jwtParser.parseSignedClaims(token).getPayload().getSubject();
        }
    }
}
