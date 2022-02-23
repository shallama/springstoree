package com.example.springstore.service;

import com.example.springstore.domain.entity.User;
import org.springframework.stereotype.Service;
/**
 * Service for work with token
 *  @author tagir
 *  @since 15.01.2022
 */
@Service
public interface TokenService {
    /**
     * Generate token and add user email to header
     * @param user entity of user
     * @return generated token
     */
    String generateToken(User user);

    /**
     * Get username (email) from token
     * @param token
     * @return username (email)
     */
    String extractUsernameAndValidate(String token);
}
