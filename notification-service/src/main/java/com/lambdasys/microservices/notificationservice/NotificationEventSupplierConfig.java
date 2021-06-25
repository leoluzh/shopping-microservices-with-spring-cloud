package com.lambdasys.microservices.notificationservice;

import com.lambdasys.microservices.notificationservice.service.EmailSender;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.function.Consumer;

@Component
public class NotificationEventSupplierConfig implements Serializable {

    @Bean
    public Consumer<Message<String>> notificationEventSupplier(){
        return message -> new EmailSender().sendMail(message.getPayload());
    }

}
