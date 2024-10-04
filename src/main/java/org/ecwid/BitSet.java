package org.ecwid;

public class BitSet {
    private final long[] bits;

    public BitSet(long size) {
        bits = new long[((int) (size / 64)) + 1];
    }

    public void set(long index) {
        int arrayIndex = (int) (index / 64);
        int bitIndex = (int) (index % 64);
        bits[arrayIndex] |= 1L << bitIndex;
    }

    public int count() {
        int count = 0;
        for (long bit : bits) {
            count += Long.bitCount(bit);
        }
        return count;
    }
}
