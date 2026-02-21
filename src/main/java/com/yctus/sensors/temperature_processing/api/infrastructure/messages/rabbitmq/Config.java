package com.yctus.sensors.temperature_processing.api.infrastructure.messages.rabbitmq;

import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.jackson.autoconfigure.JsonMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.JacksonJsonMessageConverter;
import tools.jackson.core.json.JsonWriteFeature;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;

@Configuration
public class Config {
    public static String FANAUT_EXCHANGE_NAME = "temperature-processing.received.v1.exc";

    @Bean
    public JacksonJsonMessageConverter jacksonJsonMessageConverter(JsonMapper mapper) {
//        final JsonMapper mapper = JsonMapper.builder()
//                .enable(JsonWriteFeature.ESCAPE_NON_ASCII)
//                .build();
        return new JacksonJsonMessageConverter(mapper);
    }

    @Bean
    JsonMapperBuilderCustomizer jacksonCustomizer() {
        return builder -> builder.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public FanoutExchange exchange() {
        return ExchangeBuilder.fanoutExchange(FANAUT_EXCHANGE_NAME).build();
    }
}
