package com.v0ex.security;

/**
 * Created by zbj on 17/1/5.
 */
public class EncodeDecodeUtil {

    private static final String AESPWD = "0Z1X2C3Y4H5D6J7K8O9";

    /**
     * 对原字符串进行加密
     *
     * @param oStr 原字符串
     * @return
     */
    public static String encode(String oStr){
        byte[] encryptResult = AES.encrypt(oStr, AESPWD);
        String encryptResultStr = AES.parseByte2HexStr(encryptResult);
        return encryptResultStr;
    }

    /**
     * 对加密的字符串解密
     *
     * @param encryptResultStr 加密的字符串
     * @return
     */
    public static String decode(String encryptResultStr){
        byte[] decryptFrom = AES.parseHexStr2Byte(encryptResultStr);
        byte[] decryptResult = AES.decrypt(decryptFrom,AESPWD);
        return new String(decryptResult);
    }

    public static void main(String[] args) {
        //System.out.println(encode("123456"));
        System.out.println(decode("B6320D50F248C0FAD724684EB18EE2D22FEF226D291D95B0745BEBB1C23A575B"));
    }

}
