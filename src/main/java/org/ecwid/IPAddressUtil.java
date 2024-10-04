package org.ecwid;

public class IPAddressUtil {
    public static long textToLong(String src) {
        byte[] bytes = textToNumericFormatV4(src);
        long address = 0;
        for (byte b : bytes) {
            address = (address << 8) | (b & 0xFF);
        }

        return address;
    }

    /*
     * Converts IPv4 address in its textual presentation form
     * into its numeric binary form.
     *
     * Was borrowed from sun.net.util.IPAddressUtil#textToNumericFormatV4
     *
     * @param src a String representing an IPv4 address in standard format
     * @return a byte array representing the IPv4 numeric address
     */
    private static byte[] textToNumericFormatV4(String src) {
        byte[] res = new byte[4];

        long tmpValue = 0;
        int currByte = 0;
        boolean newOctet = true;

        int len = src.length();
        if (len == 0 || len > 15) {
            return null;
        }

        for (int i = 0; i < len; i++) {
            char c = src.charAt(i);
            if (c == '.') {
                if (newOctet || tmpValue < 0 || tmpValue > 0xff || currByte == 3) {
                    return null;
                }
                res[currByte++] = (byte) (tmpValue & 0xff);
                tmpValue = 0;
                newOctet = true;
            } else {
                int digit = digit(c, 10);
                if (digit < 0) {
                    return null;
                }
                tmpValue *= 10;
                tmpValue += digit;
                newOctet = false;
            }
        }
        if (newOctet || tmpValue < 0 || tmpValue >= (1L << ((4 - currByte) * 8))) {
            return null;
        }
        switch (currByte) {
            case 0:
                res[0] = (byte) ((tmpValue >> 24) & 0xff);
            case 1:
                res[1] = (byte) ((tmpValue >> 16) & 0xff);
            case 2:
                res[2] = (byte) ((tmpValue >> 8) & 0xff);
            case 3:
                res[3] = (byte) ((tmpValue >> 0) & 0xff);
        }
        return res;
    }

    public static int digit(char c, int radix) {
        int val = c - '0';
        return (val < 0 || val >= radix) ? -1 : val;
    }
}
