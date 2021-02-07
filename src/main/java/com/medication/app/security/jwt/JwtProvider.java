package com.medication.app.security.jwt;

import com.medication.app.dto.UserPrinciple;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${medication.app.jwtSecret}")
    private String jwtSecret;

    @Value("${medication.app.jwtExpiration}")
    private int jwtExpiration;

    @Value("${jwt.request.key.keyRole}")
    private String claimKeyRole;

    @Value("${jwt.request.key.userType}")
    private String userType;

    @Value("${jwt.request.key.email}")
    private String claimKeyEmail;

    @Value("${jwt.request.key.created}")
    private String claimKeyCreated;

    @Value("${jwt.request.key.keyUserId}")
    private String claimKeyUserId;


    public String generateJwtToken(Authentication authentication) {

        UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpiration * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    private Map<String, Object> generateClaims(UserPrinciple userPrinciple) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(claimKeyEmail, userPrinciple.getUsername());
        claims.put(claimKeyCreated, LocalDate.now());
//        claims.put(claimKeyUserId, userPrinciple.getId());
        claims.put(claimKeyRole, userPrinciple.getRole());


        for (GrantedAuthority authority : userPrinciple.getAuthorities()) {
            if (isTypeAuthority(authority.getAuthority())) {
                claims.put(userType, authority.getAuthority()); //aici e pus rolul
            }
        }

        return claims;
    }

    private boolean isTypeAuthority(String auth) {
//        try {
//            return EnumUtils.isValidEnumIgnoreCase(RoleName.class, auth);
//        } catch (IllegalArgumentException ex) {
//            return false;
//        }
        return true;
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature -> Message: {} ", e);
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: {}", e);
        }

        return false;
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }
}