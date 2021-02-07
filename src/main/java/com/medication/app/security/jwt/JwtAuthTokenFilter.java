package com.medication.app.security.jwt;

import com.medication.app.dto.UserPrinciple;
import com.medication.app.service.hospital.UserDetailsServiceImplementation;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Collections.emptyList;

@Slf4j
public class JwtAuthTokenFilter extends UsernamePasswordAuthenticationFilter {

    @Value("${jwt.token.header}")
    private String tokenHeader;

    @Value("${jwt.token.prefix}")
    private String defaultHeaderValue;

    @Value("${medication.app.jwtSecret}")
    private String authSecret;

    @Autowired
    private JwtProvider tokenProvider;

    @Autowired
    private UserDetailsServiceImplementation userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthTokenFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        String token = ((HttpServletRequest) request).getHeader(tokenHeader);
        if (token == null || token.trim().equals(defaultHeaderValue)) {
            SecurityContextHolder.getContext().setAuthentication(null);
            filterChain.doFilter(request, response);
            return;
        }

        UserDetails user = authorizeToken(token);
        if (user == null) {
            SecurityContextHolder.getContext().setAuthentication(null);
            filterChain.doFilter(request, response);
            return;
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, emptyList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private UserPrinciple authorizeToken(String authenticationHeader) {
        JwtResponse jwtToken = new JwtResponse();
        jwtToken.setToken(authenticationHeader);

        UserPrinciple user = authorize(jwtToken);

        if (user == null) {
            return null;
        }

        return user;
    }

    public UserPrinciple authorize(JwtResponse jwtResponse) {
        String subjectFromAuthToken = getSubjectFromAuthToken(jwtResponse.getToken(), false);

        if (subjectFromAuthToken == null) {
            return null;
        }

        return userDetailsService.loadUserByUsername(subjectFromAuthToken);
    }

    private String getSubjectFromToken(String token, String secretKey, boolean shouldParseExpired) {
        String email;
        try {
            email = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
        } catch (ExpiredJwtException ex) {
            email = shouldParseExpired ? ex.getClaims().getSubject() : null;
        } catch (Exception ex) {
            //log.error("Error trying to parse jwt token:" + token);
            email = null;
        }
        return email;
    }

    public String getSubjectFromAuthToken(String token, boolean shouldParseExpired) {
        if (token.contains(defaultHeaderValue)) {
            token = token.replace(defaultHeaderValue, "");
        }
        return getSubjectFromToken(token, authSecret, shouldParseExpired);
    }

    private List<GrantedAuthority> mapToGrantedAuthorities(Collection<GrantedAuthority> authorities) {
        List<GrantedAuthority> grantedAuth = new ArrayList<>();

        authorities.stream()
                .map(p -> new SimpleGrantedAuthority(p.getAuthority()))
                .forEach(grantedAuth::add);

        return grantedAuth;
    }

    private String getJwt(HttpServletRequest httpServletRequest) {
        String authHeader = httpServletRequest.getHeader("Authorization");

        if(authHeader != null && authHeader.startsWith("Bearer ")){
            return  authHeader.replace("Bearer ", "");
        }

        return  null;
    }

}

