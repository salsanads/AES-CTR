package cis.view;

import cis.app.AESCTR;
import cis.lib.Util;

import java.io.*;

public class Runner {
    public static void main(String[] args) {
        byte[] plaintext = readBytesFromFile(args[0]);
        String keytext = readOneLineFromFile(args[1]);
        int keylen = keytext.length();

        if (! ((keylen == 32) || (keylen == 48) || (keylen == 64)) || (!Util.isHex(keytext))) {
            // add error message to GUI
//            lResult.setForeground(Color.red);
//            lResult.setText("Error in Key!!!");
//            taTrace.setForeground(Color.red);
//            taTrace.setText("AES key [" + hexkey +
//                    "] must be 32 or 48 or 64 hex digits long.");
//            tResult.setText("");
            return;
        }
        byte[] key = Util.hex2byte(keytext);

        AESCTR aesCtr = new AESCTR();

        byte[] resCiphertext = aesCtr.encrypt(plaintext, key);
        System.out.println("Encryption Result: " + Util.toHEX1(resCiphertext));
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
}
