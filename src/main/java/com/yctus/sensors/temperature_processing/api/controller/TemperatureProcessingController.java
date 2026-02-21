package com.yctus.sensors.temperature_processing.api.controller;


import com.yctus.sensors.temperature_processing.api.model.TemperatureLogResponse;
import com.yctus.sensors.temperature_processing.common.IdGenerator;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;

import static com.yctus.sensors.temperature_processing.api.infrastructure.messages.rabbitmq.Config.FANAUT_EXCHANGE_NAME;

@Slf4j
@RestController
@RequestMapping("/api/v1/{sensorId}/temperatures/data")
@RequiredArgsConstructor
public class TemperatureProcessingController {

    private final RabbitTemplate rabbitTemplate;

    @PostMapping(consumes = MediaType.TEXT_PLAIN_VALUE)
    public void data(@PathVariable TSID sensorId, @RequestBody String temperatureRequest) {
        if (Strings.isEmpty(temperatureRequest)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        double temperature;

        try {
            temperature = Double.parseDouble(temperatureRequest);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid temperature value", e);
        }
        TemperatureLogResponse response = TemperatureLogResponse.builder()
                .id(IdGenerator.generateId())
                .sensorId(sensorId)
                .temperature(temperature)
                .registeredAt(OffsetDateTime.now())
                .build();

        log.info(response.toString());

        String exchangeName = FANAUT_EXCHANGE_NAME;
        String routingKey = "";
        MessagePostProcessor messagePostProcessor = message -> {
            message.getMessageProperties().setContentType(MediaType.APPLICATION_JSON_VALUE);
            message.getMessageProperties().setHeader("Sensor ID: ", sensorId.toString());
            return message;
        };

        rabbitTemplate.convertAndSend(exchangeName, routingKey, response.toString(), messagePostProcessor);
    }

    @GetMapping
    public String healthCheck() {
        if (Strings.isBlank("123546")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sensor ID cannot be blank");
        }
        return "Temperature Processing Service is up and running for sensor ID: ";
    }
}