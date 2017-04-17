package cis.app;

import cis.lib.Util;

import java.io.*;

public class AESCTRDriver {

    private final static AESCTR AESCTR = new AESCTR();

    public static void doEncryption(String plaintextPath, String keyPath, String ciphertextPath) {
        byte[] plaintext = readBytesFromFile(plaintextPath);
        String keytext = readOneLineFromFile(keyPath);

        if (isKeyValid(keytext)) {
            byte[] key = Util.hex2byte(keytext);

            byte[] ciphertext = AESCTR.encrypt(plaintext, key);
            writeBytesToFile(ciphertext, ciphertextPath);
        }
    }

    public static void doDecryption(String ciphertextPath, String keyPath, String plaintextPath) {
        byte[] ciphertext = readBytesFromFile(ciphertextPath);
        String keytext = readOneLineFromFile(keyPath);

        if (isKeyValid(keytext)) {
            byte[] key = Util.hex2byte(keytext);

            byte[] plaintext = AESCTR.decrypt(ciphertext, key);
            writeBytesToFile(plaintext, plaintextPath);
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

        } catch (IOException e) {
            e.printStackTrace();
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
        } catch (Exception e) {
            // TO DO: print error message
            e.printStackTrace();
        }
        return null;
    }

    private static void writeBytesToFile(byte[] bytesArray, String filePath) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            fileOutputStream.write(bytesArray);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isKeyValid(String keytext) {
        int keylen = keytext.length();

        if (! ((keylen == 32) || (keylen == 48) || (keylen == 64)) || (!Util.isHex(keytext))) {
            // add error message to GUI
//            lResult.setForeground(Color.red);
//            lResult.setText("Error in Key!!!");
//            taTrace.setForeground(Color.red);
//            taTrace.setText("AES key [" + hexkey +
//                    "] must be 32 or 48 or 64 hex digits long.");
//            tResult.setText("");
            return false;
        }
        return true;
    }
}
