package edu.itplus.bibliosping.backend;

import edu.itplus.bibliosping.backend.model.User;
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
    @PostConstruct
    public void PostConstruct() {
        User test = new User();
        test.setUsername("Lajos");
        test.setPassword("123456");

        System.out.println(loginService.login(test));
        System.out.println(test);
    }
}