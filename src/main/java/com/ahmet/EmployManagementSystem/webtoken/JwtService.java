package com.ahmet.EmployManagementSystem.webtoken;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class JwtService {

    private static final String SECRET = "5F62E4B99C8C71C0F05CD327EF57F36B31AA55FD0EFA3FBF66E521B7511D9F2349841ACC1EBBFEACD24C7CB8A2214261EEA6967C7EE101DBAFDC2D19BC8B87E1";
    private static final Long VALIDITY = TimeUnit.MINUTES.toMillis(180);

    public String generateToken(UserDetails userDetails) {
        Map<String, String> claims = new HashMap<>();
        claims.put("username", userDetails.getUsername());
        claims.put("role", userDetails.getAuthorities().toString());

        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusMillis(VALIDITY)))
                .signWith(generateKey())
                .compact();
    }

    private SecretKey generateKey() {
        byte[] decodedKey = Base64.getDecoder().decode(SECRET);
        return Keys.hmacShaKeyFor(decodedKey);

    }
}
