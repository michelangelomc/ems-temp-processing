package com.yctus.sensors.temperature_processing;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedEpochRandomGenerator;

import java.util.UUID;

public class IdGenerator {
    private static final TimeBasedEpochRandomGenerator uuidGenerator = Generators.timeBasedEpochRandomGenerator();

    public IdGenerator() {
    }

    public static UUID generateId() {
        return uuidGenerator.generate();
    }
}
