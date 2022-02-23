package com.example.springstore.security;

import com.example.springstore.domain.dto.user.UserDto;
import com.example.springstore.domain.mapper.UserMapper;
import com.example.springstore.repository.UserRepository;
import com.example.springstore.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
/**
 *  Filter to check validity of the token
 *  @author tagir
 *  @since 15.01.2022
 */
@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JwtRequestFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @SneakyThrows
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String token = request.getHeader("AUTHORIZATION");
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (token != null && securityContext.getAuthentication() == null) {
            String username = tokenService.extractUsernameAndValidate(token);
            UserDto user = userRepository.findByEmail(username)
                    .map(userMapper::toDto)
                    .orElseThrow();
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name())));
            securityContext.setAuthentication(usernamePasswordAuthenticationToken);
        }
        filterChain.doFilter(request, response);
    }
}
