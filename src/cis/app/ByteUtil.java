package cis.app;

import java.math.BigInteger;
import java.nio.ByteBuffer;

public class ByteUtil {
    private static ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
    private static final int AES_BLOCK_SIZE = 16;

    static byte[] longToBytes(long x) {
        buffer.putLong(0, x);
        return buffer.array();
    }

    static byte[] to16Bytes(BigInteger bigInt) {
        byte[] bigIntByte = bigInt.toByteArray();
        byte[] result = new byte[AES_BLOCK_SIZE];
        if (bigIntByte.length > AES_BLOCK_SIZE) {
            // exceed the size
            System.out.println("Exceed the Size");
            return null;
        }
        System.arraycopy(bigIntByte, 0, result, AES_BLOCK_SIZE - bigIntByte.length, bigIntByte.length);
        return result;
    }

    static byte[] xor16Bytes(byte[] arr1, byte[] arr2) {
        byte[] result = new byte[AES_BLOCK_SIZE];
        for (int i = 0; i < AES_BLOCK_SIZE; i++) {
            result[i] = (byte) ((int) arr1[i] ^ (int) arr2[i]);
        }
        return result;
    }
}
