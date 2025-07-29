package edu.itplus.bibliosping.backend;

import edu.itplus.bibliosping.backend.model.User;
import edu.itplus.bibliosping.backend.repository.UserDAO;
import edu.itplus.bibliosping.backend.servises.LoginService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    @Autowired
    private LoginService loginService;
    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);
    }

    @Autowired
    private UserDAO userDAO;

    @PostConstruct
    public void PostConstruct() {
        User test = new User();
        test.setUsername("Almaspite");
        test.setPassword("1234567");
        userDAO.findByID(1L);
        System.out.println(userDAO.findByID(1L).getUsername());
        System.out.println(userDAO.findAll());
        System.out.println(userDAO.findbyUsername("Feri"));
        //test.setUsername("Lajos");
        //test.setPassword("123456");

        //System.out.println(loginService.login(test));
        //System.out.println(test);
    }
}