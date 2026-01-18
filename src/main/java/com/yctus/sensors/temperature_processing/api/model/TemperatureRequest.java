package com.yctus.sensors.temperature_processing.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TemperatureRequest {
    Double temperature;
}
