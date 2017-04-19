package cis.app;

import cis.lib.Util;
import cis.view.Frame;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * The driver to run AES-CTR starting with reading the input and key file,
 * then performing encryption or decryption, last writing the output to a file.
 */
public class AESCTRDriver {
    private final static AESCTR AESCTR = new AESCTR();

    private final static JLabel messageField = Frame.getMessageComponent().getMessageField();

    /**
     * Process all input path to encrypt plaintext.
     *
     * @param plaintextPath the plaintext path value to encrypt.
     * @param keyPath the 128-bit, 192-bit, or 256-bit key path value to encrypt.
     * @param ciphertextPath the encrypted ciphertext path value.
     */
    public static void doEncryption(String plaintextPath, String keyPath, String ciphertextPath) {
        byte[] plaintext = readBytesFromFile(plaintextPath);
        if (plaintext == null) {
            return;
        }

        String keytext = readOneLineFromFile(keyPath);
        if (keytext == null) {
            return;
        }

        if (isKeyValid(keytext)) {
            byte[] key = Util.hex2byte(keytext);

            byte[] ciphertext = AESCTR.encrypt(plaintext, key);
            writeBytesToFile(ciphertext, ciphertextPath);

            messageField.setForeground(Color.GREEN);
            messageField.setText("Encryption succeed.");
        }
    }

    /**
     * Process all input path to decrypt ciphertext.
     *
     * @param ciphertextPath the ciphertext path value to decrypt.
     * @param keyPath the 128-bit, 192-bit, or 256-bit key path value to decrypt.
     * @param plaintextPath the decrypted plaintext path value.
     */
    public static void doDecryption(String ciphertextPath, String keyPath, String plaintextPath) {
        byte[] ciphertext = readBytesFromFile(ciphertextPath);
        if (ciphertext == null) {
            return;
        }

        String keytext = readOneLineFromFile(keyPath);
        if (keytext == null) {
            return;
        }

        if (isKeyValid(keytext)) {
            byte[] key = Util.hex2byte(keytext);

            byte[] plaintext = AESCTR.decrypt(ciphertext, key);
            writeBytesToFile(plaintext, plaintextPath);

            messageField.setForeground(Color.GREEN);
            messageField.setText("Decryption succeed.");
        }
    }

    /**
     * Reads the bytes from a file.
     *
     * @param filePath the path to file.
     * @return a byte array containing the bytes read from the file.
     */
    private static byte[] readBytesFromFile(String filePath) {
        FileInputStream fileInputStream = null;
        byte[] bytesInput;

        try {

            File file = new File(filePath);
            bytesInput = new byte[(int) file.length()];

            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bytesInput);

        } catch (FileNotFoundException e) {
            writeFileNotFoundMessage(filePath);
            return null;
        } catch (IOException e) {
            writeFailedReadingMessage(filePath);
            return null;
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return bytesInput;
    }

    /**
     * Reads one line from a file.
     *
     * @param filePath the path to file.
     * @return a line containing the string read from the file.
     */
    private static String readOneLineFromFile(String filePath) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            return br.readLine();
        } catch (FileNotFoundException e) {
            writeFileNotFoundMessage(filePath);
        } catch (IOException e) {
            writeFailedReadingMessage(filePath);
        }
        return null;
    }

    /**
     * Writes the bytes to a file.
     *
     * @param bytesArray the byte array with the bytes to write.
     * @param filePath the path to the file.
     */
    private static void writeBytesToFile(byte[] bytesArray, String filePath) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            fileOutputStream.write(bytesArray);
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            writeFileNotFoundMessage(filePath);
        } catch (IOException e) {
            writeFailedWritingMessage(filePath);
        }
    }

    /**
     * Validates key value.
     *
     * @param keytext the 128-bit, 192-bit, or 256-bit key value to validation check.
     * @return true if the key valid; false if the key is not valid.
     */
    private static boolean isKeyValid(String keytext) {
        int keylen = keytext.length();

        if (! ((keylen == 32) || (keylen == 48) || (keylen == 64))) {
            messageField.setForeground(Color.RED);
            messageField.setText("AES key must be 32 or 48 or 64 long. But, [" + keytext + "] is " + keylen + " long.");
            return false;
        } else if (!Util.isHex(keytext)) {
            messageField.setForeground(Color.RED);
            messageField.setText("AES key must be hex digits.");
            return false;
        }
        return true;
    }

    /**
     * Writes a message if file not found.
     *
     * @param filePath the path to the file.
     */
    private static void writeFileNotFoundMessage(String filePath) {
        messageField.setForeground(Color.RED);
        messageField.setText("File " + filePath + " is not found.");
    }

    /**
     * Writes a message if failed reading.
     *
     * @param filePath the path to the file.
     */
    private static void writeFailedReadingMessage(String filePath) {
        messageField.setForeground(Color.RED);
        messageField.setText("Failed reading " + filePath + ".");
    }

    /**
     * Writes a message if failed writing.
     *
     * @param filePath the path to the file.
     */
    private static void writeFailedWritingMessage(String filePath) {
        messageField.setForeground(Color.RED);
        messageField.setText("Failed writing " + filePath + ".");
    }
}
