package com.v0ex.security;

import javax.crypto.*;
import java.io.*;
import java.security.*;

/**
 * Created by zbj on 16/1/25.
 */
public class RSATest {

    private static final int KEYSIZE = 512;

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, ClassNotFoundException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, ShortBufferException, BadPaddingException {
        if (args[0].equals("-genkey")){
            KeyPairGenerator pairGenerator = KeyPairGenerator.getInstance("RSA");
            SecureRandom secureRandom = new SecureRandom();
            pairGenerator.initialize(KEYSIZE,secureRandom);
            KeyPair keyPair = pairGenerator.generateKeyPair();
            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(args[1]))){
                outputStream.writeObject(keyPair.getPublic());
            }
            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(args[2]))){
                outputStream.writeObject(keyPair.getPrivate());
            }

        }else if (args[0].equals("-encrypt")){
            KeyGenerator keyGenerator = KeyGenerator.getInstance("RSA");
            SecureRandom random = new SecureRandom();
            keyGenerator.init(random);
            SecretKey secretKey = keyGenerator.generateKey();
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(args[3]));
                 DataOutputStream out = new DataOutputStream(new FileOutputStream(args[2]));
                 InputStream in = new FileInputStream(args[1])){
                Key key = (Key) inputStream.readObject();
                Cipher cipher = Cipher.getInstance("RSA");
                cipher.init(Cipher.WRAP_MODE,key);
                byte[] wrap = cipher.wrap(key);
                out.writeInt(wrap.length);
                out.write(wrap);

                cipher = Cipher.getInstance("AES");
                cipher.init(Cipher.ENCRYPT_MODE,secretKey);
                SecurityUtil.crypt(in,out,cipher);
            }
        }else {
            try (DataInputStream in = new DataInputStream(new FileInputStream(args[1]));
                ObjectInputStream keyIn = new ObjectInputStream(new FileInputStream(args[3]));
                OutputStream out = new FileOutputStream(args[2]);){
                int length = in.readInt();
                byte[] wrappedkey = new byte[length];
                in.read(wrappedkey,0,length);
                Key privateKey = (Key) keyIn.readObject();
                Cipher cipher = Cipher.getInstance("RSA");
                cipher.init(Cipher.UNWRAP_MODE,privateKey);
                Key key = cipher.unwrap(wrappedkey, "AES", Cipher.SECRET_KEY);
                cipher = Cipher.getInstance("AES");
                cipher.init(Cipher.DECRYPT_MODE,key);
                SecurityUtil.crypt(in,out,cipher);
            }
        }
    }
}
