package com.smartclinic.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.util.Date;

@Service
public class TokenService {

  @Value("${jwt.secret}")
  private String secret;

  private Key key() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
  }

  public String generateToken(String email) {
    return Jwts.builder()
        .setSubject(email)
        .setIssuedAt(new Date())
        .setExpiration(Date.from(Instant.now().plusSeconds(60*60*24)))
        .signWith(key(), SignatureAlgorithm.HS256)
        .compact();
  }

  public String validateAndGetEmail(String token) {
    try {
      return Jwts.parserBuilder().setSigningKey(key()).build()
          .parseClaimsJws(token).getBody().getSubject();
    } catch (JwtException e) {
      return null;
    }
  }
}
