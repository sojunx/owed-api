package dev.sojunx.owed.config;

import dev.sojunx.owed.response.UserDTO;
import dev.sojunx.owed.service.JwtService;
import dev.sojunx.owed.service.UserService;
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
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService service;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
        String header = req.getHeader(HttpHeaders.AUTHORIZATION);
        var context = SecurityContextHolder.getContext();

        if (header != null && context.getAuthentication() == null && header.regionMatches(true, 0, "Bearer ", 0, 7)) {
            String token = header.substring(7).trim();

            if (!token.isEmpty()) {
                // TODO: Handle expired token
                UUID id = service.parse(token);
                UserDTO user = userService.getUserByID(id);

                // TODO: Some routes not need fully authenticated, some routes just need user_id
                if (user != null) {
                    var auth = new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
                    context.setAuthentication(auth);
                }

            }
        }

        chain.doFilter(req, res);
    }
}
