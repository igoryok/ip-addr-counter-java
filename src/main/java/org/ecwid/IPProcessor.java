package org.ecwid;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class IPProcessor {
    private static final String IP_ADDRESS_FILE = "src/test/resources/ip_addresses.txt";

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        System.out.println("Number of unique ips - " + getUniqueIPCount(Paths.get(IP_ADDRESS_FILE)));

        long end = System.currentTimeMillis();
        System.out.println("Elapsed Time in milli seconds: " + (end - start));
    }

    public static long getUniqueIPCount(Path path) {

        // Read a file line by line
        try (Stream<String> stream = Files.lines(path, StandardCharsets.US_ASCII)) {
            BitSet bitSet = new BitSet((long) Math.pow(2, 32));

            stream.forEach(ip -> {
                if (ip == null || ip.isEmpty()) {
                    return;
                }
                bitSet.set(IPAddressUtil.textToLong(ip));
            });

            return bitSet.count();
        } catch (Exception e) {
            System.out.println("Can't read file: " + path);
            return 0;
        }
    }
}