package com.RetoBack.RetoBack.Config;

import com.RetoBack.RetoBack.model.Role;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.RetoBack.RetoBack.Jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        final String[] AUTH_WHITE_LIST = {
                "/auth/**",
                "/h2-console/**"
        };

        return http
                .csrf(csrf ->
                        csrf
                                .disable())
                .authorizeHttpRequests(authRequest ->
                        {
                            try {
                                authRequest
                                        //.antMatchers("/h2-console/**").permitAll()
                                        .antMatchers(AUTH_WHITE_LIST).permitAll()
                                        //.and()
                                        //.csrf().ignoringAntMatchers("/h2-console/**")
                                        //.requestMatchers("/auth/**")).permitAll()
                                        //.antMatchers("/api/post/**").hasRole(String.valueOf(Role.USER))
                                        //.anyRequest().hasRole(String.valueOf(Role.ADMIN))
                                        .anyRequest().authenticated()
                                        .and().headers().frameOptions().sameOrigin();


                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                )
                .sessionManagement(sessionManager->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }
}
