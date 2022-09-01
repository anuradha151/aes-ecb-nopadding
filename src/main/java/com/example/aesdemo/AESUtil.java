package com.example.aesdemo;


import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Objects;

public class AESUtil {

    public static String encrypt(String algorithm, String input, String key)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        SecretKey secretKey = getSecretKey(key);
        byte[][] bytes = divideArray(input.getBytes(), 16);

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] cipherText = {};
        for (int i = 0; i < bytes.length; i++) {
            cipherText = concat(cipherText, cipher.update(bytes[i]));
            if (i == (bytes.length - 1)) {
                cipherText = concat(cipherText, cipher.doFinal(bytes[i]));
            }
        }

        StringBuilder str = new StringBuilder();
        for (byte b : cipherText) {
            str.append(String.format("%02x", b));
        }

        return str.toString();
    }

    private static byte[] concat(byte[]... arrays) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        if (arrays != null) {
            Arrays.stream(arrays).filter(Objects::nonNull)
                    .forEach(array -> out.write(array, 0, array.length));
        }
        return out.toByteArray();
    }

    public static byte[][] divideArray(byte[] source, int chunksize) {
        byte[][] ret = new byte[(int) Math.ceil(source.length / (double) chunksize)][chunksize];
        int start = 0;
        for (int i = 0; i < ret.length; i++) {
            ret[i] = Arrays.copyOfRange(source, start, start + chunksize);
            start += chunksize;
        }
        return ret;
    }

    private static SecretKey getSecretKey(String key) {
        byte[] bytes = convertHexToByteArray(key);
        return new SecretKeySpec(bytes, 0, bytes.length, "AES");
    }

    private static byte[] convertHexToByteArray(String key) {
        byte[] ans = new byte[key.length() / 2];
        for (int i = 0; i < ans.length; i++) {
            int index = i * 2;
            int val = Integer.parseInt(key.substring(index, index + 2), 16);
            ans[i] = (byte) val;
        }
        return ans;
    }

}