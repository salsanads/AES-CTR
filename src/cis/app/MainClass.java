package cis.app;

import cis.lib.Util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Basic IO example with CTR using AES
 */
public class MainClass {
    public static void main(String[] args) throws Exception {
        String plainString = "000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f";
        byte[] input = Util.hex2byte(plainString);
        String keytext = "000102030405060708090a0b0c0d0e0f";
        byte[] keyBytes = Util.hex2byte(keytext);
        byte[] ivBytes = new byte[] { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x05 };

        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        Cipher cipher = Cipher.getInstance("AES/CTR/PKCS5Padding");
        System.out.println("input : " + plainString);

        // encryption pass
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
        ByteArrayInputStream bIn = new ByteArrayInputStream(input);
        CipherInputStream cIn = new CipherInputStream(bIn, cipher);
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();

        int ch;
        while ((ch = cIn.read()) >= 0) {
            bOut.write(ch);
        }

        byte[] cipherText = bOut.toByteArray();

        System.out.println("cipher: " + Util.toHEX1(cipherText));

        // decryption pass
        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
        bOut = new ByteArrayOutputStream();
        CipherOutputStream cOut = new CipherOutputStream(bOut, cipher);
        cOut.write(cipherText);
        cOut.close();
        System.out.println("plain : " + Util.toHEX1(bOut.toByteArray()));
    }
}
