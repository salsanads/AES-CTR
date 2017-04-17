package cis.app;

import cis.lib.Util;
import cis.view.Frame;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class AESCTRDriver {
    private final static AESCTR AESCTR = new AESCTR();

    private final static JLabel messageField = Frame.getMessageComponent().getMessageField();

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
            if (ciphertext != null) {
                writeBytesToFile(ciphertext, ciphertextPath);
            }

            messageField.setForeground(Color.GREEN);
            messageField.setText("Encryption succeed.");
        }
    }

    public static void doDecryption(String ciphertextPath, String keyPath, String plaintextPath) {
        byte[] ciphertext = readBytesFromFile(ciphertextPath);
        String keytext = readOneLineFromFile(keyPath);

        if (isKeyValid(keytext)) {
            byte[] key = Util.hex2byte(keytext);

            byte[] plaintext = AESCTR.decrypt(ciphertext, key);
            if (plaintext != null) {
                writeBytesToFile(plaintext, plaintextPath);
            }

            messageField.setForeground(Color.GREEN);
            messageField.setText("Decryption succeed.");
        }
    }

    private static byte[] readBytesFromFile(String filePath) {
        FileInputStream fileInputStream = null;
        byte[] bytesInput = null;

        try {

            File file = new File(filePath);
            bytesInput = new byte[(int) file.length()];

            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bytesInput);

        } catch (FileNotFoundException e) {
            writeFileNotFoundMessage(filePath);
        } catch (IOException e) {
            writeFailedReadingMessage(filePath);
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

    private static void writeFileNotFoundMessage(String filePath) {
        messageField.setForeground(Color.RED);
        messageField.setText("File " + filePath + " is not found.");
    }

    private static void writeFailedReadingMessage(String filePath) {
        messageField.setForeground(Color.RED);
        messageField.setText("Failed reading " + filePath + ".");
    }

    private static void writeFailedWritingMessage(String filePath) {
        messageField.setForeground(Color.RED);
        messageField.setText("Failed writing " + filePath + ".");
    }
}
