package com.khandora.it.config;

import com.khandora.it.filter.CorsFilter;
import com.khandora.it.security.jwt.JwtConfigurer;
import com.khandora.it.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;

    private static final String ADMIN_ENDPOINT = "/admin/**";
    private static final String LOGIN_ENDPOINT = "/login/**";
    private static final String USER_ENDPOINT = "/user/**";

    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public CorsFilter CorsFilter() {
        return new CorsFilter();
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(LOGIN_ENDPOINT).permitAll()
                .antMatchers(ADMIN_ENDPOINT).hasRole("ADMIN")
                .antMatchers(USER_ENDPOINT).hasRole("USER")
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }

}
