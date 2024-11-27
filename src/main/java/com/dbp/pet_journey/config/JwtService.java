package com.dbp.pet_journey.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class JwtService {

    @Value("${jwt.secret}")
    private String jwtSigningKey;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        if (token == null || userDetails == null) {
            log.warn("Token validation failed - token or userDetails is null");
            log.warn("Token: {}", token);
            log.warn("UserDetails: {}", userDetails);
            return false;
        }

        try {
            final String userName = extractUserName(token);
            boolean isValid = (userName != null
                    && userName.equals(userDetails.getUsername())
                    && !isTokenExpired(token));

            log.info("Token validation result: {}", isValid);
            log.info("Extracted username: {}", userName);
            log.info("UserDetails username: {}", userDetails.getUsername());

            return isValid;
        } catch (Exception e) {
            log.error("Token validation error", e);
            return false;
        }
    }

    public boolean isTokenExpired(String token) {
        try {
            return extractExpiration(token).before(new Date());
        } catch (ExpiredJwtException e) {
            log.warn("Token has expired");
            return true;
        } catch (Exception e) {
            log.error("Error checking token expiration", e);
            return true;
        }
    }

    public List<String> extractRoles(String token) {
        Claims claims = extractAllClaims(token);
        return (List<String>) claims.get("roles");
    }

    private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        // Agregar roles a las reclamaciones extra
        extraClaims.put("roles", userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));

        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        try {
            final Claims claims = extractAllClaims(token);
            return claimsResolvers.apply(claims);
        } catch (Exception e) {
            log.error("Error extracting claim", e);
            return null;
        }
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (SignatureException e) {
            log.error("Invalid JWT signature", e);
            throw e;
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired", e);
            throw e;
        } catch (Exception e) {
            log.error("Error parsing JWT token", e);
            throw new RuntimeException("Invalid token", e);
        }
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Optional: Method to refresh token
    public String refreshToken(String token) {
        try {
            final Claims claims = extractAllClaims(token);
            claims.setIssuedAt(new Date(System.currentTimeMillis()));
            claims.setExpiration(new Date(System.currentTimeMillis() + jwtExpiration));

            return Jwts.builder()
                    .setClaims(claims)
                    .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                    .compact();
        } catch (Exception e) {
            log.error("Error refreshing token", e);
            return null;
        }
    }
}