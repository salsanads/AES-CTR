import static org.junit.Assert.assertEquals;

import cis.app.AESCTR;
import cis.lib.Util;
import org.junit.Test;

/**
 * Collection of tests for AESCTR class.
 */
public class AESCTRTest {

    private AESCTR aesCtr = new AESCTR();

    /**
     *  Tests 16 bytes key complete block.
     */
    @Test
    public void test16BytesKeyCompleteBlock() {
        String keytext = "000102030405060708090a0b0c0d0e0f";
        byte[] key = Util.hex2byte(keytext);

        String plainString = "000102030405060708090a0b0c0d0e0f";
        byte[] plaintext = Util.hex2byte(plainString);

        String cipherString = "9ff5aed973b1cc6d7bdc77bc34a34b8a";
        byte[] ciphertext = Util.hex2byte(cipherString);

        byte[] resCiphertext = aesCtr.encrypt(plaintext, key);
        byte[] resPlaintext = aesCtr.decrypt(ciphertext, key);

        assertEquals(Util.toHEX1(ciphertext), Util.toHEX1(resCiphertext));
        assertEquals(Util.toHEX1(plaintext), Util.toHEX1(resPlaintext));
    }

    /**
     *  Tests 16 bytes key incomplete block.
     */
    @Test
    public void test16BytesKeyIncompleteBlock() {
        String keytext = "000102030405060708090a0b0c0d0e0f";
        byte[] key = Util.hex2byte(keytext);

        String plainString = "00010203040506070809";
        byte[] plaintext = Util.hex2byte(plainString);

        String cipherString = "9ff5aed973b1cc6d7bdc7bb13ea84383";
        byte[] ciphertext = Util.hex2byte(cipherString);

        byte[] resCiphertext = aesCtr.encrypt(plaintext, key);
        byte[] resPlaintext = aesCtr.decrypt(ciphertext, key);

        assertEquals(Util.toHEX1(ciphertext), Util.toHEX1(resCiphertext));
        assertEquals((Util.toHEX1(plaintext)), Util.toHEX1(resPlaintext));
    }

    /**
     *  Tests 16 bytes key complete block string.
     */
    @Test
    public void test16BytesKeyCompleteBlockString() {
        String keytext = "000102030405060708090a0b0c0d0e0f";
        byte[] key = Util.hex2byte(keytext);

        String plainString = "In a fictional town of wood-frame houses and cobblestone streets, inspired mainly by the real life location of Colmar, France, Cocoa arrives at the cafe Rabbit House, assuming there are rabbits to be cuddled. What Cocoa actually found were her boarding house for her new high school and a small shy girl named Chino with an angora rabbit on her head. She quickly befriends Chino with full intentions of becoming her older sister. From there she would begin her new life and befriend many others, including the soldier-like Rize, the spacey Chiya, and the dignified Syaro.";
        byte[] plaintext = plainString.getBytes();

        String cipherString = "d69a8cbb57d2a30907bc12d959c265f15390e0dce782c7a6305c230445aebc1034955a479af493862da00c0d9349c1e5723558aff62861c4ccbf45e2cf23bcaa87b1c77f8a567fcbf45def8d815dc9c074b76b2f7505ea4aea0b725d5493f6c54ccd479c036b041a361e7b7b9bbc44a69edddd9e5c67077118e13fee6af24b63789d69a1411f3af4019c0bf5e4e6c1723fe8685b043fc2a95afb7e206ce7b5bfa86db3af8b559fa65d4c873703b88e9e9076737b32fba6cf1e69281d4ae72c00738725be9539b6e8d8a7677e491091221176a0b21483a369869a0d5436b0a42fcabdfb770b8441005f13ca7ebd4281ab18c87db5a9bba33e60df500ca3737d5697879cd3d4b6119d2da68e041caef61471e6bad4270a83ffd55524fd1b4131c9d81de45b54d50d6b3b9901c9924c84e2dfb459f3bdbba64564b02fb28cdf71d2ef19faf1c968c2523fddb30586b24e886c3930ec2f65184dda9716ac64e7eb4054366db7f1b50f322a22f7b066ade75122d33d693ad990147352b48e9dcc7f93e16c2ef0017e1b3bda568b61343f8864237484e308e013293482df359bf2614b3f0cb7c1f5b6de05ad41b4d4f495ce2a764442a1662e0e0ee9f1bffd84302eb3bc19eab07fbbd9e0a059f2ef54f6b15b21a7dbda3b8066b83b533c12d6613f9f836797ddf463d93786226ce6bf52483bdbfbf88e429a0e6f2ad222895b9010c085938a3b3db1c46b43ddcd2c3fc4c15a52eaad5ab70ace634df4397cd7b179452e335616a05da4b955344134854208181a636fe8454fe76dab2d7450150d2e74";
        byte[] ciphertext = Util.hex2byte(cipherString);

        byte[] resCiphertext = aesCtr.encrypt(plaintext, key);
        byte[] resPlaintext = aesCtr.decrypt(ciphertext, key);

        assertEquals(Util.toHEX1(ciphertext), Util.toHEX1(resCiphertext));
        assertEquals(Util.toHEX1(plaintext), Util.toHEX1(resPlaintext));
    }

    /**
     *  Tests 24 bytes key complete block.
     */
    @Test
    public void test24BytesKeyCompleteBlock() {
        String keytext = "000102030405060708090a0b0c0d0e0f0001020304050607";
        byte[] key = Util.hex2byte(keytext);

        String plainString = "000102030405060708090a0b0c0d0e0f";
        byte[] plaintext = Util.hex2byte(plainString);

        String cipherString = "ef6dc0994ff494be65d543f444192bfd";
        byte[] ciphertext = Util.hex2byte(cipherString);

        byte[] resCiphertext = aesCtr.encrypt(plaintext, key);
        byte[] resPlaintext = aesCtr.decrypt(ciphertext, key);

        assertEquals(Util.toHEX1(ciphertext), Util.toHEX1(resCiphertext));
        assertEquals((Util.toHEX1(plaintext)), Util.toHEX1(resPlaintext));
    }

    /**
     *  Tests 24 bytes key incomplete block.
     */
    @Test
    public void test24BytesKeyIncompleteBlock() {
        String keytext = "000102030405060708090a0b0c0d0e0f0001020304050607";
        byte[] key = Util.hex2byte(keytext);

        String plainString = "00010203040506070809";
        byte[] plaintext = Util.hex2byte(plainString);

        String cipherString = "ef6dc0994ff494be65d54ff94e1223f4";
        byte[] ciphertext = Util.hex2byte(cipherString);

        byte[] resCiphertext = aesCtr.encrypt(plaintext, key);
        byte[] resPlaintext = aesCtr.decrypt(ciphertext, key);

        assertEquals(Util.toHEX1(ciphertext), Util.toHEX1(resCiphertext));
        assertEquals((Util.toHEX1(plaintext)), Util.toHEX1(resPlaintext));
    }

    /**
     *  Tests 32 bytes key complete block.
     */
    @Test
    public void test32BytesKeyCompleteBlock() {
        String keytext = "000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f";
        byte[] key = Util.hex2byte(keytext);

        String plainString = "000102030405060708090a0b0c0d0e0f";
        byte[] plaintext = Util.hex2byte(plainString);

        String cipherString = "958e0029ca74d040d0db094cc2feec7c";
        byte[] ciphertext = Util.hex2byte(cipherString);

        byte[] resCiphertext = aesCtr.encrypt(plaintext, key);
        byte[] resPlaintext = aesCtr.decrypt(ciphertext, key);

        assertEquals(Util.toHEX1(ciphertext), Util.toHEX1(resCiphertext));
        assertEquals(Util.toHEX1(plaintext), Util.toHEX1(resPlaintext));
    }

    /**
     *  Tests 32 bytes key incomplete block.
     */
    @Test
    public void test32BytesKeyIncompleteBlock() {
        String keytext = "000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f";
        byte[] key = Util.hex2byte(keytext);

        String plainString = "00010203040506070809";
        byte[] plaintext = Util.hex2byte(plainString);

        String cipherString = "958e0029ca74d040d0db0541c8f5e475";
        byte[] ciphertext = Util.hex2byte(cipherString);

        byte[] resCiphertext = aesCtr.encrypt(plaintext, key);
        byte[] resPlaintext = aesCtr.decrypt(ciphertext, key);

        assertEquals(Util.toHEX1(ciphertext), Util.toHEX1(resCiphertext));
        assertEquals((Util.toHEX1(plaintext)), Util.toHEX1(resPlaintext));
    }
}
