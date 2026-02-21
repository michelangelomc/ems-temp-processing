package com.yctus.sensors.temperature_processing.api.infrastructure.messages.rabbitmq;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class Initializer {
    private final RabbitAdmin rabbitAdmin;

    @PostConstruct
    public void starter() {
        rabbitAdmin.initialize();
    }
}
