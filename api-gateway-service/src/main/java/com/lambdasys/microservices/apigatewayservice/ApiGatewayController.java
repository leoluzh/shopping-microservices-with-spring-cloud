package com.lambdasys.microservices.apigatewayservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

import java.io.Serializable;

@RestController
public class ApiGatewayController implements Serializable {

    @GetMapping("/")
    public Mono<String> home(final WebSession webSession){
        return Mono.just(webSession.getId());
    }

}
