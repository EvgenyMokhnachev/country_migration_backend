package ru.sfedu.ictis.mohnachev.countrymigration.domain.auth;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class PassHashUtil {

    private final MessageDigest md5Inst;
    {
        try {
            md5Inst = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String createMD5Hash(final String input) {
        byte[] messageDigest = md5Inst.digest(input.getBytes());
        return convertToHex(messageDigest);
    }

    private String convertToHex(final byte[] messageDigest) {
        BigInteger bigint = new BigInteger(1, messageDigest);
        String hexText = bigint.toString(16);
        while (hexText.length() < 32) {
            hexText = "0".concat(hexText);
        }
        return hexText;
    }

}
