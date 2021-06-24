package com.lambdasys.microservices.apigatewayservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import java.io.Serializable;

@EnableWebFluxSecurity
@Configuration
public class SecurityConfig implements Serializable {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(final ServerHttpSecurity httpSecurity) {
        httpSecurity.authorizeExchange(exchanges -> exchanges.anyExchange().authenticated())
                .oauth2Client(Customizer.withDefaults());
        httpSecurity.csrf().disable();
        return httpSecurity.build();
    }

}
