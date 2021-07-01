package com.lambdasys.microservices.orderservice.exception;

import org.springframework.http.HttpStatus;

public class ApplicationException extends Exception {

    private HttpStatus status;

    public ApplicationException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus(){
        return this.status;
    }

}
