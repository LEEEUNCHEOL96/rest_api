package com.example.rest_api.global.jwt;

import com.example.rest_api.domain.member.entity.Member;
import com.example.rest_api.global.Util.Util;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {
    @Value("${custom.jwt.secretKey}")
    private String secretKeyOrigin;

    private SecretKey cachedSecretKey;
    // 시크릿키 가지고 오기
    public SecretKey getSecretKey() {
        if (cachedSecretKey == null) cachedSecretKey = _getSecretKey();
        return cachedSecretKey;
    }
    // 스크릿 키 인코딩
    private SecretKey _getSecretKey() {
        String keyBase64Encoded = Base64.getEncoder().encodeToString(secretKeyOrigin.getBytes());
        return Keys.hmacShaKeyFor(keyBase64Encoded.getBytes());
    }
    // refreshToken 토큰 만들기
    public String genRefreshToken(Member member) {
        return genToken(member, 60 * 60 * 24 * 365 * 1);
    }
    // accessToken 만들기
    public String genAccessToken(Member member) {
        return genToken(member, 60 * 10);
    }
    // 토큰 생성
    public String genToken (Member member, int seconds) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", member.getId());
        claims.put("username", member.getUsername());
        long now = new Date().getTime();
        Date accessTokenExpiresIn = new Date(now + 1000L * seconds);
        return Jwts.builder()
                .claim("body", Util.json.toStr(claims))
                .setExpiration(accessTokenExpiresIn)
                .signWith(getSecretKey(), SignatureAlgorithm.HS512)
                .compact();
    }
    // 토큰 유효성 검증
    public boolean verify(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSecretKey())
                    .build()
                    .parseClaimsJws(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    // 클레임 정보 받아오기
    public Map<String, Object> getClaims(String token) {
        String body = Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("body", String.class);
        return Util.toMap(body);
    }
}