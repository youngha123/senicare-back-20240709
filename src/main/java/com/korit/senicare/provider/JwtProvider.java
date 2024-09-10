package com.korit.senicare.provider;

import java.util.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import java.security.Key;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nimbusds.jose.util.StandardCharset;

import io.jsonwebtoken.security.Keys;

// class: JWT 생성 및 검증 기능 제공자
// - JWT 암호화 알고리즘 HS256
// - 비밀키 환경변수에 있는 jwt.secret
// - JWT 만료기간 10시간

@Component
public class JwtProvider {
    
    @Value("${jwt.secret}")
    private String secretKey;

    // JWT 생성 메서드
    public String create(String userId) {

        // 만료 시간 = 현재시간 + 10시간
        Date expiredDate = Date.from(Instant.now().plus(10, ChronoUnit.HOURS));

        String jwt = null;

        try {

            // JWT 암호화에 사용할 Key 생성
            Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharset.UTF_8));
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

        return jwt;

    }

}
