package com.yctus.sensors.temperature_processing;

import com.yctus.sensors.temperature_processing.common.IdGenerator;
import com.yctus.sensors.temperature_processing.common.UUIdv7Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

class UUIDv7Test {

    @Test
    void shouldGeneratorUUIDv7() {
        UUID u1 = IdGenerator.generateId();
        UUID u2 = IdGenerator.generateId();
        UUID u3 = IdGenerator.generateId();
        UUID u4 = IdGenerator.generateId();

        System.out.println(UUIdv7Utils.extractTimestampFromUUIDv7(u1));
        System.out.println(UUIdv7Utils.extractTimestampFromUUIDv7(u2));
        System.out.println(UUIdv7Utils.extractTimestampFromUUIDv7(u3));
        System.out.println(UUIdv7Utils.extractTimestampFromUUIDv7(u4));

        OffsetDateTime ofdt = UUIdv7Utils.extractTimestampFromUUIDv7(u1).truncatedTo(ChronoUnit.MINUTES);
        OffsetDateTime now = OffsetDateTime.now().truncatedTo(ChronoUnit.MINUTES);

        System.out.println("ofdt: " + ofdt);
        System.out.println("now: " + now);

        Assertions.assertNotEquals(ofdt, now);
    }
}
