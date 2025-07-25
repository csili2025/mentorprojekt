package edu.itplus.bibliosping.backend.utils.impl;

import edu.itplus.bibliosping.backend.utils.PasswordEncrypter;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
@Component
@Profile("JDBC")
public class PassworEncrypterSha265 implements PasswordEncrypter {
    public String hashPassword(String password, String salt) {
       try {
           MessageDigest md = MessageDigest.getInstance("SHA-256");
           byte[] input = (password + salt).getBytes();
           md.reset();
           md.update(input);

           byte[] out = md.digest();
           StringBuffer sb = new StringBuffer();
           for (byte output : out) {
               sb.append(String.format("%02X",output));
           }
           return sb.toString();
       }catch (NoSuchAlgorithmException e) {
           throw new RuntimeException(e);
       }

    }
}
