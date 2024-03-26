package com.board.security;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.SIG;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JwtService {
    private static final Long VALIDITY_TIME = 60 * 60 * 12 * 1000L;

    public String createToken(String username) {
        return JwtUtil.createJwt(username, VALIDITY_TIME);
    }

    private static class JwtUtil {
        private static final JwtBuilder jwtBuilder;

        static {
            SecretKey secretKey = SIG.HS256.key().build();
            jwtBuilder = Jwts.builder().signWith(secretKey);
        }

        public static String createJwt(String username, Long validTimeMillis) {
            return jwtBuilder
                    .subject(username)
                    .issuedAt(new Date())
                    .expiration(new Date(System.currentTimeMillis() + validTimeMillis))
                    .compact();
        }
    }

}
