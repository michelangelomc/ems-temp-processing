package com.yctus.sensors.temperature_processing;

import org.springframework.core.SpringVersion;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

public class UUIdv7Utils {
    private UUIdv7Utils() {
    }

    public static OffsetDateTime extractTimestampFromUUIDv7(UUID uuid) {
        if(uuid != null) {
            long mostSigBits = uuid.getMostSignificantBits();
            long timestamp = (mostSigBits & 0x0FFFFFFFFFFFFFFFL) >>> 16;
            return OffsetDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneOffset.UTC);
        }

        return  null;
    }
}
