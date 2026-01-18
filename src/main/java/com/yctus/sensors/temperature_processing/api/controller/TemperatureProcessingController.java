package com.yctus.sensors.temperature_processing.api.controller;


import com.yctus.sensors.temperature_processing.api.model.TemperatureLogResponse;
import com.yctus.sensors.temperature_processing.common.IdGenerator;
import io.hypersistence.tsid.TSID;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;

@Slf4j
@RestController
@RequestMapping("/api/v1/{sensorId}/temperatures/data")
public class TemperatureProcessingController {

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
    }

    @GetMapping
    public String healthCheck() {
        if (Strings.isBlank("123546")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sensor ID cannot be blank");
        }
        return "Temperature Processing Service is up and running for sensor ID: ";
    }
}