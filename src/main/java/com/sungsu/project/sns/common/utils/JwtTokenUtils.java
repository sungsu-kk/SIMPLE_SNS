package com.sungsu.project.sns.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

public class JwtTokenUtils {

    public static Boolean validateToken(String token, String userName, String key){
        String usernameByToken = getUserName(token, key);
        return usernameByToken.equals(userName) && !isTokenExpired(token, key);
    }

    public static Claims extractAllClaims(String token, String key){

        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey(key))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public static String getUserName(String token, String key){
        return extractAllClaims(token, key).get("userName", String.class);
    }

    public static Key getSigningKey(String secreatekey){
        byte[] bytes = secreatekey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(bytes);
    }

    public static Boolean isTokenExpired(String token, String key) {
        Date expiration = extractAllClaims(token, key).getExpiration();
        return expiration.before(new Date());
    }

    public static String generateAccessToken(String username, String key, long expiredTimeMs) {
        return doGenerateToken(username, expiredTimeMs, key);
    }

    private static String doGenerateToken(String username, long expireTime, String key) {
        Claims claims = Jwts.claims();
        claims.put("username", username);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(getSigningKey(key), SignatureAlgorithm.HS256)
                .compact();
    }

}
