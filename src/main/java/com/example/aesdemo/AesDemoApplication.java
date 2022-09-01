package com.example.aesdemo;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

//@SpringBootApplication
public class AesDemoApplication {

    public static void main(String[] args) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String input = "Your input text";
        String secret = "YourSecret";
        String algorithm = "AES/ECB/NoPadding";


        String encryptedText = AESUtil.encrypt(algorithm, input, secret);

        System.out.println(encryptedText);

    }


}
