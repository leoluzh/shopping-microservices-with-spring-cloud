package com.lambdasys.microservices.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderFailureException extends Exception {

    public OrderFailureException(String message){
        super(message);
    }

}
