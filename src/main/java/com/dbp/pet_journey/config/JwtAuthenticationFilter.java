package com.dbp.pet_journey.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import com.dbp.pet_journey.config.JwtService;
import com.dbp.pet_journey.auth.domain.UserDetailsServiceImpl;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j  // Añade esta anotación para logging
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsServiceImpl userService;

    @Autowired
    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsServiceImpl userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            // Loguea toda la información del encabezado para depuración
            log.info("Request URL: {}", request.getRequestURL());
            log.info("Authorization Header: {}", request.getHeader("Authorization"));

            final String authHeader = request.getHeader("Authorization");

            // Añade más logging para entender qué está pasando
            if (authHeader == null) {
                log.warn("Authorization header is null");
                filterChain.doFilter(request, response);
                return;
            }

            if (!StringUtils.hasText(authHeader) || !StringUtils.startsWithIgnoreCase(authHeader, "Bearer")) {
                log.warn("Invalid Authorization header: {}", authHeader);
                filterChain.doFilter(request, response);
                return;
            }

            // Añade verificación de seguridad adicional
            String jwt = null;
            try {
                jwt = authHeader.substring(7);
                log.info("Extracted JWT token: {}", jwt);
            } catch (Exception e) {
                log.error("Error extracting JWT token", e);
                filterChain.doFilter(request, response);
                return;
            }

            // Verifica que el token no esté vacío
            if (!StringUtils.hasText(jwt)) {
                log.warn("JWT token is empty after extraction");
                filterChain.doFilter(request, response);
                return;
            }

            String userEmail = jwtService.extractUserName(jwt);
            log.info("Extracted username: {}", userEmail);

            if (StringUtils.hasText(userEmail) && SecurityContextHolder.getContext().getAuthentication() == null) {
                try {
                    UserDetails userDetails = userService.loadUserByUsername(userEmail);

                    if (jwtService.isTokenValid(jwt, userDetails)) {
                        SecurityContext context = SecurityContextHolder.createEmptyContext();
                        List<GrantedAuthority> authorities = jwtService.extractRoles(jwt).stream()
                                .map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toList());
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        context.setAuthentication(authToken);
                        SecurityContextHolder.setContext(context);
                    } else {
                        log.warn("Invalid JWT token for user: {}", userEmail);
                    }
                } catch (Exception e) {
                    log.error("Error processing user authentication", e);
                }
            }

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.error("Unexpected error in JWT Authentication Filter", e);
            filterChain.doFilter(request, response);
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            log.info("User roles: {}",
                    auth.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(Collectors.toList())
            );
        }
    }
}