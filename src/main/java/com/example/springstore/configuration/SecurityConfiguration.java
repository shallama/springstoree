package com.example.springstore.configuration;

import com.example.springstore.security.JwtRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *  Spring security configuration
 *  @author tagir
 *  @since 20.02.2022
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    /** Inject filter for check validation of token    */
    private final JwtRequestFilter filter;

    /**
     * Inject Password Encoder Bean
     * @return BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Add for configuration ignoring of auth endpoints
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.ignoring().antMatchers("/auth/login", "/auth/sign-up", "/v3/api-docs",
                                                "/configuration/ui",
                                                "/swagger-resources/**",
                                                "/configuration/security",
                                                "/swagger-ui.html",
                                                "/webjars/**");
    }

    /**
     *  Add JwtRequestFilter to begin of the filter chain
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.cors().disable()
                .csrf().disable();
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }
}
