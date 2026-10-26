package dev.sojunx.owed.config;

import dev.sojunx.owed.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService service;
    private static final Set<String> EXCLUDED_PATHS = Set.of("/api/v1/auth/signup", "/api/v1/auth/signin");

    @Override
    protected boolean shouldNotFilter(HttpServletRequest req) {
        return EXCLUDED_PATHS.contains(req.getServletPath());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
        String header = req.getHeader(HttpHeaders.AUTHORIZATION);
        var context = SecurityContextHolder.getContext();

        if (header != null && context.getAuthentication() == null && header.regionMatches(true, 0, "Bearer ", 0, 7)) {
            String token = header.substring(7).trim();

            if (!token.isEmpty()) {
                UUID id = service.parse(token);
                var auth = new UsernamePasswordAuthenticationToken(id, null, Collections.emptyList());
                context.setAuthentication(auth);
            }
        }

        chain.doFilter(req, res);
    }
}