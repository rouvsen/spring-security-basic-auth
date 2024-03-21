package com.rouvsen.springsecuritybasicauth.security;

import com.rouvsen.springsecuritybasicauth.model.Role;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

//    @Bean
//    public H2ConsoleProperties h2ConsoleProperties() {
//        return new H2ConsoleProperties();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity security,
            HandlerMappingIntrospector introspector
    ) throws Exception {
        MvcRequestMatcher.Builder mvcRequestMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
        security
                .headers(x -> x.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .csrf(csrfConfig ->
                        csrfConfig
                                .ignoringRequestMatchers(mvcRequestMatcherBuilder.pattern("/public/**"))
                                .ignoringRequestMatchers(PathRequest.toH2Console())
                )
                .authorizeHttpRequests(x ->
                        x.requestMatchers(PathRequest.toH2Console()).hasRole(Role.ROLE_ADMIN.getValue())
                         .requestMatchers(mvcRequestMatcherBuilder.pattern("/public/**")).permitAll()
                         .requestMatchers(mvcRequestMatcherBuilder.pattern("/private/**")).hasAnyRole(
                                 Role.ROLE_ADMIN.getValue(),
                                 Role.ROLE_USER.getValue()
                                )
                         .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(x -> x.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));
        return security.build();
    }

}
