package com.example.usersync.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue webhookQueue() {
        return new Queue("webhookQueue", false);
    }
}
