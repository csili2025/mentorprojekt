package edu.itplus.bibliosping.backend.servises.impl;

import edu.itplus.bibliosping.backend.utils.PasswordEncrypter;

public class TestPasswordDAO implements PasswordEncrypter {

    public static final String password ="123";
    public static final String salt = "salt";
    public static final String hashedPassword = "hashed123";

    @Override
    public String hashPassword(String passs, String salt) {
        if(passs.equals(password) && salt.equals(TestPasswordDAO.salt)) {
            return hashedPassword;
        } else throw new IllegalArgumentException("Hashed passwords do not match");
    }
}
