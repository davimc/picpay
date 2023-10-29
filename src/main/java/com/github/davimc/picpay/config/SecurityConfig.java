package com.github.davimc.picpay.config;

import com.github.davimc.picpay.components.SecurityFilter;
import com.github.davimc.picpay.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityFilter securityFilter;

    /** TODO
     *  colocar o filtro para transctions
     *  httpmethod.post hasRole("ROLE_USER")
     */

    @Bean
    @Profile("test")
    @Order(1)
    public SecurityFilterChain h2SecurityFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher(PathRequest.toH2Console()).csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
        return http.build();

    }
    @Bean
    @Order(2)
    public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.POST,"/auth/login/**")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.POST,"/auth/register/**")).permitAll()

                        .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.GET, "/persons/**")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.POST, "/persons/**")).permitAll()

                        .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.GET, "/wallets/**")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.POST, "/wallets/**")).authenticated()

                        .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.POST, "/transfers")).hasRole("USUARIO")
                        .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.POST, "/wallets/**")).authenticated()

                        .requestMatchers(mvcMatcherBuilder.pattern("/h2-console/**")).permitAll()
// TODO aberto para verificar h2, modificar
                        .anyRequest().permitAll()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .headers(header -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
