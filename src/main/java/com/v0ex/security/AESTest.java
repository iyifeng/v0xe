package com.v0ex.security;

import javax.crypto.*;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by zbj on 16/1/25.
 */
public class AESTest {

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, ClassNotFoundException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, ShortBufferException, IllegalBlockSizeException {
        if (args[0].equals("-genkey")){
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            SecureRandom random = new SecureRandom();
            keyGenerator.init(random);
            SecretKey secretKey = keyGenerator.generateKey();
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(args[1]))){
                out.writeObject(secretKey);
            }
        }else {
            int mode;
            if (args[0].equals("-encrypt")){
                mode = Cipher.ENCRYPT_MODE;
            }else {
                mode = Cipher.DECRYPT_MODE;
            }
            try (ObjectInputStream keyIn = new ObjectInputStream(new FileInputStream(args[3]));
                InputStream in = new FileInputStream(args[1]);
                OutputStream out = new FileOutputStream(args[2]);
            ){
                Key key = (Key) keyIn.readObject();
                Cipher cipher = Cipher.getInstance("AES");
                cipher.init(mode,key);
                SecurityUtil.crypt(in,out,cipher);
            }
        }
    }


}
