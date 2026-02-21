package com.yctus.sensors.temperature_processing.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.hypersistence.tsid.TSID;
import lombok.Builder;
import lombok.Data;
import tools.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
@JsonSerialize
public class TemperatureLogResponse implements Serializable {

    private static final long serialVersionUID = -2090710875644496851L;

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("sensorId")
    private TSID sensorId;

    @JsonProperty("registeredAt")
    private OffsetDateTime registeredAt;

    @JsonProperty("temperature")
    private Double temperature;
}