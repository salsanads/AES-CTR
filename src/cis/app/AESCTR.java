package cis.app;

import cis.lib.AES;

import java.math.BigInteger;
import java.util.Arrays;

public class AESCTR {
    private final int BLOCK_SIZE_BYTES = 16;
    private final BigInteger DEFAULT_NONCE = new BigInteger("179425817");
    private final BigInteger INCREMENT = new BigInteger("1");
    private final AES AES = new AES();

    public byte[] encrypt(byte[] plaintext, byte[] key) {
        BigInteger nonce = DEFAULT_NONCE;
        int numBlock = plaintext.length / BLOCK_SIZE_BYTES;
        int numLeftBytes = plaintext.length % BLOCK_SIZE_BYTES;
        int ciphertextSize = plaintext.length;
        ciphertextSize += (numLeftBytes == 0) ? 0 : BLOCK_SIZE_BYTES - numLeftBytes;
        byte[] ciphertext = new byte[ciphertextSize];

        AES.setKey(key);

        for (int i = 0; i < plaintext.length; i += BLOCK_SIZE_BYTES) {
            byte[] nonceArray = ByteUtil.to16Bytes(nonce);

            if (nonceArray == null) {
                return null;
            }

            byte[] curPlainBlock;
            if (i == BLOCK_SIZE_BYTES * numBlock && numLeftBytes != 0) {
                byte[] leftBytes = Arrays.copyOfRange(plaintext, i, i + numLeftBytes);
                curPlainBlock =  pkcs5Padding(leftBytes);
            } else {
                curPlainBlock = Arrays.copyOfRange(plaintext, i, i + BLOCK_SIZE_BYTES);
            }
            byte[] curEncryptedNonce = AES.encrypt(nonceArray);
            byte[] xorResult = ByteUtil.xor16Bytes(curPlainBlock, curEncryptedNonce);

            System.arraycopy(xorResult, 0, ciphertext, i, BLOCK_SIZE_BYTES);
            nonce = nonce.add(INCREMENT);
        }
        return ciphertext;
    }

    public byte[] decrypt(byte[] ciphertext, byte[] key) {
        byte[] paddedCiphertext = encrypt(ciphertext, key);

        if (paddedCiphertext == null) {
            return null;
        }

        int numBlock = paddedCiphertext.length / BLOCK_SIZE_BYTES;
        int firstIndexOfLastBlock = (numBlock - 1) * BLOCK_SIZE_BYTES;
        byte[] lastBlock = Arrays.copyOfRange(paddedCiphertext, firstIndexOfLastBlock, paddedCiphertext.length);
        for (int i = 1; i < BLOCK_SIZE_BYTES; i++) {
            byte pad = ByteUtil.intHexToByte(i);
            for (int j = BLOCK_SIZE_BYTES - 1; j > BLOCK_SIZE_BYTES - 1 - i; j--) {
                if (lastBlock[j] != pad) {
                    break;
                } else if (lastBlock[j - 1] == pad) {
                    return Arrays.copyOfRange(paddedCiphertext, 0, paddedCiphertext.length - i);
                }
            }
        }
        return paddedCiphertext;
    }

    public byte[] pkcs5Padding(byte[] initialBlock) {
        byte[] result = new byte[BLOCK_SIZE_BYTES];
        byte padding = (byte) (BLOCK_SIZE_BYTES - initialBlock.length);
        Arrays.fill(result, padding);
        System.arraycopy(initialBlock, 0, result, 0, initialBlock.length);
        return result;
    }
}
