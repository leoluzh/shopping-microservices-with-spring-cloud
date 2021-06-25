package com.lambdasys.microservices.notificationservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Slf4j
@Service
public class EmailSender implements Serializable {

    public void sendMail(final String orderNumber){
        log.info("Order has been place successfully - Order Number is {}",orderNumber);
        log.info("Email sent for Order Id {}",orderNumber);
    }

}
