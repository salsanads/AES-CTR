import static org.junit.Assert.assertEquals;

import cis.app.AESCTR;
import cis.lib.Util;
import org.junit.Test;

public class AESCTRTest {

    AESCTR aesCtr = new AESCTR();

    @Test
    public void test16BytesKeyCompleteBlock() {
        String keytext = "000102030405060708090a0b0c0d0e0f";
        byte[] key = Util.hex2byte(keytext);

        String plainString = "000102030405060708090a0b0c0d0e0f";
        byte[] plaintext = Util.hex2byte(plainString);

        String cipherString = "9b839b8a60778746485729d69310de14";
        byte[] ciphertext = Util.hex2byte(cipherString);

        byte[] resCiphertext = aesCtr.encrypt(plaintext, key);
        byte[] resPlaintext = aesCtr.decrypt(ciphertext, key);

        assertEquals(Util.toHEX1(ciphertext), Util.toHEX1(resCiphertext));
        assertEquals(Util.toHEX1(plaintext), Util.toHEX1(resPlaintext));
    }

    @Test
    public void test16BytesKeyCompleteBlockString() {
        String keytext = "000102030405060708090a0b0c0d0e0f";
        byte[] key = Util.hex2byte(keytext);

        String plainString = "In a fictional town of wood-frame houses and cobblestone streets, inspired mainly by the real life location of Colmar, France, Cocoa arrives at the cafe Rabbit House, assuming there are rabbits to be cuddled. What Cocoa actually found were her boarding house for her new high school and a small shy girl named Chino with an angora rabbit on her head. She quickly befriends Chino with full intentions of becoming her older sister. From there she would begin her new life and befriend many others, including the soldier-like Rize, the spacey Chiya, and the dignified Syaro.";
        byte[] plaintext = plainString.getBytes();

        String cipherString = "8fcf1b56a7e932e11b26ee0cc0a4f80d1c317db5faa694692614d9ce03864c672cf6ef3cece8c3ffc3e8140c40e2dfffdbc14e5d4005ac5d702e422cf9d288251c43dfb1795cb2c2e07671bfa700bfd3e2a2fbf04406e924602c46bcf33dbc72b23bdc3e07cace9ea8bd47c7ce243621d65e4278b40718afccb945b08357335aca47a80cd92eafea9ea0307ef0d83eb3152a017a7c52453b57be6e99885d62282426fb47e6b0634d838f2b51c6fa930dc673f3b282b563e8090be492f33ef8a4148591d23806ea301243983ba9d19228ebcbf36ecbbe4461fb99624eec1eb6ba5379c0a9c22ab0d2daa7cb20835a5b44d1177bae36a4f6b0e5128be75b0cc2caa600ce56cb0dd45654836a1fde8f9edf2df4c583430f897f9bed77b908ed79b6152a75370e9c23d9b609d8bd1c64c2483b303b1828b5e35fb3d34791e60c4b6f84bf986d6dd2e445ca8b2d3a9f2be68e31d178c45a206ed8ede267fa2836a40d9b1fc87b22296b89a452524cbdc3c4f7af59b290edad2b46eda5cc9bce33547998f5cfc9264a001c7ebc28606b8acf851bd6a8207182d2b355db23ea5d45cb3edc5618e81e4ff50af028ad6ed157e3bde19d93f30ddec248d3ec2b915b6902ce76de9d04f0bb6976e9fb273c7db4d467d9a685d8931027b263340d24c965ff14175f2ca032179f163a088a7c7d3fd01927d22b752a3ede4573d88bd1e592313d08320d7ba669f91989de33b83c4517997847ff74837e1da1262c230ba917686373c2511130a9305ae86df4ce8dc7e043580a71bcc2bc6e49a2b42ebaf1d9c579";
        byte[] ciphertext = Util.hex2byte(cipherString);

        byte[] resCiphertext = aesCtr.encrypt(plaintext, key);
        byte[] resPlaintext = aesCtr.decrypt(ciphertext, key);

        System.out.println(Util.toHEX1(resPlaintext));
        assertEquals(Util.toHEX1(ciphertext), Util.toHEX1(resCiphertext));
        assertEquals(Util.toHEX1(plaintext), Util.toHEX1(resPlaintext));
    }

    @Test
    public void test32BytesKeyCompleteBlock() {
        String keytext = "000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f";
        byte[] key = Util.hex2byte(keytext);

        String plainString = "000102030405060708090a0b0c0d0e0f";
        byte[] plaintext = Util.hex2byte(plainString);

        String cipherString = "e689f1cf8997b29c019d65ad0c9d1456";
        byte[] ciphertext = Util.hex2byte(cipherString);

        byte[] resCiphertext = aesCtr.encrypt(plaintext, key);
        byte[] resPlaintext = aesCtr.decrypt(ciphertext, key);

        assertEquals(Util.toHEX1(ciphertext), Util.toHEX1(resCiphertext));
        assertEquals(Util.toHEX1(plaintext), Util.toHEX1(resPlaintext));
    }

    @Test
    public void test32BytesKeyIncompleteBlock() {
        String keytext = "000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f";
        byte[] key = Util.hex2byte(keytext);

        String plainString = "00010203040506070809";
        byte[] plaintext = Util.hex2byte(plainString);

        String cipherString = "e689f1cf8997b29c019d6fa600901a59";
        byte[] ciphertext = Util.hex2byte(cipherString);

        byte[] resCiphertext = aesCtr.encrypt(plaintext, key);
        byte[] resPlaintext = aesCtr.decrypt(ciphertext, key);

        assertEquals(Util.toHEX1(ciphertext), Util.toHEX1(resCiphertext));
        assertEquals((Util.toHEX1(plaintext) + "000000000000"), Util.toHEX1(resPlaintext));
    }
}
