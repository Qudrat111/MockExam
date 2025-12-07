package org.example.mockexam.configs.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.mockexam.enums.UserStatus;
import org.example.mockexam.modules.entity.User;
import org.example.mockexam.services.AuthService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.example.mockexam.configs.util.ConstantUtil.AUTH_HEADER;
import static org.example.mockexam.configs.util.TokenUtil.extractBearerToken;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    private final AuthService userDetailsService;

    public JwtFilter(JwtProvider jwtProvider, AuthService userDetailsService) {
        this.jwtProvider = jwtProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String bearerToken = extractBearerToken(request.getHeader(AUTH_HEADER));

        if (bearerToken == null || !jwtProvider.isValidated(bearerToken)) {
            SecurityContextHolder.getContext().setAuthentication(null);
            filterChain.doFilter(request, response);
            return;
        }

        String username = jwtProvider.extractUsername(bearerToken);

        if (username == null) {
            SecurityContextHolder.getContext().setAuthentication(null);
            filterChain.doFilter(request, response);
            return;
        }

        User userDetails = (User) userDetailsService.loadUserByUsername(username);

        if (userDetails.getStatus() == UserStatus.INACTIVE) {
            SecurityContextHolder.getContext().setAuthentication(null);
            filterChain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
