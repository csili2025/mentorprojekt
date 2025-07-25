package edu.itplus.bibliosping.backend.utils;

public interface PasswordEncrypter {
     String hashPassword(String passs, String salt);
}
