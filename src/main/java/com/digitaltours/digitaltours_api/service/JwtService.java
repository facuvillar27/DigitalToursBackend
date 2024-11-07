package com.digitaltours.digitaltours_api.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    private final String SECRET_KEY = "37f923f638cda3d57444937f89b1d4c92a66aafaa6a51efda9ddae91fad4ba6ba2e79e3872c3112a6052e8965b047880ce19cf2379f6153c4e240e1c6a688ee49a1cda61fd8012ed32f6e060b409a462500e7c27556d81baea25a5d99b160d67180c6ef27373cbe096427efe9ece7ebf69bb219a347e7f388d126219482581e79666186720ef387f4be3082bc912ac420a96a197fd77d7b3b89928f2b90b03a571826b6859550fb437694516eb650a8bac49a3db00ad31f0fae87a2c48dd5bca00655302052b6a8bd7ccc73c8e06a8136edc319cb51b8f6bf852a04c09783e6603dbe38d1ecea182ba27df2f48f122fb3d338bc2998d260e0b548f96dbc30c95";
    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hora

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }
}