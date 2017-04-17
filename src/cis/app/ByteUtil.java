package cis.app;

import cis.view.Frame;

import javax.swing.*;
import java.awt.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;

public class ByteUtil {
    private static final int AES_BLOCK_SIZE = 16;

    private final static JLabel messageField = Frame.getMessageComponent().getMessageField();

    static byte intHexToByte(int x) {
        byte[] arrayByte = ByteBuffer.allocate(Integer.BYTES).putInt(x).array();
        return arrayByte[Integer.BYTES - 1];
    }

    static byte[] to16Bytes(BigInteger bigInt) {
        byte[] bigIntByte = bigInt.toByteArray();
        byte[] result = new byte[AES_BLOCK_SIZE];
        if (bigIntByte.length > AES_BLOCK_SIZE) {
            messageField.setForeground(Color.RED);
            messageField.setText("Cannot continue the process, the counter is exceed 16 bytes.");
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
