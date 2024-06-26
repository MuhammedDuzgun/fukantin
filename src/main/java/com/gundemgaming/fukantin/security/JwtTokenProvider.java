package com.gundemgaming.fukantin.security;

import com.gundemgaming.fukantin.exception.FuKantinAPIException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app-jwt-expiration-milliseconds}")
    private long expirationDate;

    //generate jwt token
    public String generateToken(Authentication authentication) {

        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() +  expirationDate);

        String token = Jwts.builder()
                .subject(username)
                .issuedAt(currentDate)
                .expiration(expireDate)
                .signWith(key())
                .compact();

        return token;
    }

    //get username from JWT token
    public String getUsername(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith((SecretKey) key())
                    .build()
                    .parse(token);
            return true;
        } catch (MalformedJwtException malformedJwtException) {
            throw new FuKantinAPIException("Invalid Jwt Token", HttpStatus.BAD_REQUEST);
        } catch (ExpiredJwtException expiredJwtException) {
            throw new FuKantinAPIException("Expired Jwt token", HttpStatus.BAD_REQUEST);
        } catch (UnsupportedJwtException unsupportedJwtException) {
            throw new FuKantinAPIException("Unsupported Jwt token", HttpStatus.BAD_REQUEST);
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new FuKantinAPIException("Jwt claims string is Empty or null", HttpStatus.BAD_REQUEST);
        }
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

}
