package com.lambdasys.microservices.orderservice;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.instrument.async.TraceableExecutorService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderServiceConfig {

    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";

    private final BeanFactory beanFactory;

    @Bean
    public RequestInterceptor requestInterceptorForTokenBearer() {
        return requestTemplate -> {
            final var token = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
            requestTemplate.header(AUTHORIZATION, BEARER + token.getToken().getTokenValue());
        };
    }

    @Bean
    public ExecutorService traceableExecutorService() {
        var executorService = Executors.newCachedThreadPool();
        return new TraceableExecutorService(beanFactory, executorService);
    }

}
