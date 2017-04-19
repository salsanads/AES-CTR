package cis.app;

import java.math.BigInteger;
import java.nio.ByteBuffer;

/**
 * Collection of utility routines for converting, xor-int, and displaying binary
 * saved in hex/int/bigInt/byte arrays, then loaded/displayed using bytes.
 */
public class ByteUtil {
    private static final int AES_BLOCK_SIZE = 16;

    /**
     * Utility method to convert a hexadecimal to a byte.
     *
     * @param x a hexadecimal integer to be converted into byte.
     * @return byte representation of hexadecimal integer.
     */
    static byte intHexToByte(int x) {
        byte[] arrayByte = ByteBuffer.allocate(Integer.BYTES).putInt(x).array();
        return arrayByte[Integer.BYTES - 1];
    }

    /**
     * Utility method to convert a big integer to array of 16 bytes.
     *
     * @param bigInt a big integer to be converted into array of 16 bytes.
     * @return byte representation of big integer.
     */
    static byte[] to16Bytes(BigInteger bigInt) {
        byte[] bigIntByte = bigInt.toByteArray();
        byte[] result = new byte[AES_BLOCK_SIZE];
        if (bigIntByte.length > AES_BLOCK_SIZE) {
            return null;
        }
        System.arraycopy(bigIntByte, 0, result, AES_BLOCK_SIZE - bigIntByte.length, bigIntByte.length);
        return result;
    }

    /**
     * Utility method to xor two array of bytes.
     *
     * @param arr1 array of bytes 1 to be xor.
     * @param arr2 array of bytes 2 to be xor.
     * @return byte representation of xor operation between two array of bytes.
     */
    static byte[] xor16Bytes(byte[] arr1, byte[] arr2) {
        byte[] result = new byte[AES_BLOCK_SIZE];
        for (int i = 0; i < AES_BLOCK_SIZE; i++) {
            result[i] = (byte) ((int) arr1[i] ^ (int) arr2[i]);
        }
        return result;
    }
}
