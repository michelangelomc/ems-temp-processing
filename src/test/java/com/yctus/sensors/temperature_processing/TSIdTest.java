package com.yctus.sensors.temperature_processing;

import io.hypersistence.tsid.TSID;
import org.junit.jupiter.api.Test;

class TSIdTest {

    @Test
    void shouldTSIdTest() {
        TSID tsIdFac = TSID.Factory.builder().build().generate();
        TSID tsid = TSID.fast();
        System.out.println("tsid: " + tsid);
        System.out.println("tsid: " + tsid.toLong());

        System.out.println(tsIdFac);
    }
}
