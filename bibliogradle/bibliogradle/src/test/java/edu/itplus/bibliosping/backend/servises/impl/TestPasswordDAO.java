package edu.itplus.bibliosping.backend.servises.impl;

import edu.itplus.bibliosping.backend.utils.PasswordEncrypter;

public class TestPasswordDAO implements PasswordEncrypter {

    @Override
    public String hashPassword(String passs, String salt) {
        if(passs.equals("123") && salt.equals("salt")) {
            return "hashed1234";
        } else throw new IllegalArgumentException("Hashed passwords do not match");
    }
}
