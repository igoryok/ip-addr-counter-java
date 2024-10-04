package org.ecwid;

import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IPProcessorTest {

    @Test
    public void getUniqueIPCountTest() {
        long result = IPProcessor.getUniqueIPCount(Paths.get("src/test/resources/ip_addresses.txt"));

        assertEquals(10, result);
    }

}
