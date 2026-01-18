package com.yctus.sensors.temperature_processing.api.model;

import io.hypersistence.tsid.TSID;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
public class TemperatureLogResponse implements Serializable {

    private static final long serialVersionUID = -2090710875644496851L;

    private UUID id;
    private TSID sensorId;
    private OffsetDateTime registeredAt;
    private Double temperature;
}