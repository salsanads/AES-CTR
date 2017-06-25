package cis.app;

import cis.lib.AES;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * Implementation of the AES CTR mode encryption and decryption using PKCS#5 padding in java.
 */
public class AESCTR {
    private final int BLOCK_SIZE_BYTES = 16;
    private final BigInteger DEFAULT_NONCE = new BigInteger("179425817");
    private final BigInteger INCREMENT = new BigInteger("1");
    private final AES AES = new AES();

    /**
     * AES encrypt plaintext using key previously set.
     *
     * @param plaintext the plaintext value to encrypt.
     * @param key the 128-bit, 192-bit, or 256-bit key value to encrypt.
     * @return the encrypted ciphertext value.
     */
    public byte[] encrypt(byte[] plaintext, byte[] key) {
        BigInteger nonce = DEFAULT_NONCE;
        int numBlock = plaintext.length / BLOCK_SIZE_BYTES;
        int numLeftBytes = plaintext.length % BLOCK_SIZE_BYTES;
        int ciphertextSize = plaintext.length;
        ciphertextSize += (numLeftBytes == 0) ? 0 : BLOCK_SIZE_BYTES - numLeftBytes;
        byte[] ciphertext = new byte[ciphertextSize];

        AES.setKey(key);

        // iterate through plaintext block
        for (int i = 0; i < plaintext.length; i += BLOCK_SIZE_BYTES) {
            byte[] nonceArray = ByteUtil.to16Bytes(nonce);

            // reset the nonce if exceed 16 bytes
            if (nonceArray == null) {
                nonce = new BigInteger("0");
                nonceArray = ByteUtil.to16Bytes(nonce);
            }

            byte[] curPlainBlock;
            // check whether current plaintext block is the last block and doesn't contain 16 bytes
            if (i == BLOCK_SIZE_BYTES * numBlock && numLeftBytes != 0) {
                byte[] leftBytes = Arrays.copyOfRange(plaintext, i, i + numLeftBytes);
                // do padding since the original last block doesn't contain 16 bytes
                curPlainBlock =  pkcs5Padding(leftBytes);
            } else {
                curPlainBlock = Arrays.copyOfRange(plaintext, i, i + BLOCK_SIZE_BYTES);
            }
            // encrypt the nonce using AES
            byte[] curEncryptedNonce = AES.encrypt(nonceArray);
            // xor the encryption result with current plaintext block
            byte[] xorResult = ByteUtil.xor16Bytes(curPlainBlock, curEncryptedNonce);

            System.arraycopy(xorResult, 0, ciphertext, i, BLOCK_SIZE_BYTES);
            // increment the nonce for the next block
            nonce = nonce.add(INCREMENT);
        }
        return ciphertext;
    }

    /**
     * AES decrypt ciphertext using key previously set.
     *
     * @param ciphertext the plaintext value to decrypt.
     * @param key the 128-bit, 192-bit, or 256-bit key value to decrypt.
     * @return the decrypted plaintext value.
     */
    public byte[] decrypt(byte[] ciphertext, byte[] key) {
        // CTR decryption is same with encryption
        byte[] plaintext = encrypt(ciphertext, key);
        return pkcs5RemovePadding(plaintext);
    }

    /**
     * PKCS5Padding pads the plaintext by the method described in the PKCS#5 standards.
     *
     * @param initialBlock byte array containing the plaintext to be padded.
     * @return the plaintext with padding.
     */
    public byte[] pkcs5Padding(byte[] initialBlock) {
        byte[] result = new byte[BLOCK_SIZE_BYTES];
        byte padding = (byte) (BLOCK_SIZE_BYTES - initialBlock.length);

        // fill the result array with padding first
        Arrays.fill(result, padding);
        // replace the elements of result array along the block length with elements from the block
        System.arraycopy(initialBlock, 0, result, 0, initialBlock.length);
        return result;
    }

    /**
     * PKCS5Padding removes padding that found in plaintext.
     *
     * @param plaintext byte array containing the plaintext and may be ended with padding.
     * @return the plaintext without any padding.
     */
    public byte[] pkcs5RemovePadding(byte[] plaintext) {
        int numBlock = plaintext.length / BLOCK_SIZE_BYTES;
        int firstIndexOfLastBlock = (numBlock - 1) * BLOCK_SIZE_BYTES;
        // get the last block to be checked whether padded or not
        byte[] lastBlock = Arrays.copyOfRange(plaintext, firstIndexOfLastBlock, plaintext.length);

        // iterate through the possibility of padding
        for (int i = 1; i < BLOCK_SIZE_BYTES; i++) {
            byte padding = ByteUtil.intHexToByte(i);
            // iterate along the padding size
            for (int j = BLOCK_SIZE_BYTES - 1; j > BLOCK_SIZE_BYTES - 1 - i; j--) {
                if (lastBlock[j] != padding) {
                    break;
                } else if (lastBlock[j - 1] != padding) {
                    // truncate the plaintext to remove padding
                    return Arrays.copyOfRange(plaintext, 0, plaintext.length - i);
                }
            }
        }
        return plaintext;
    }
}
