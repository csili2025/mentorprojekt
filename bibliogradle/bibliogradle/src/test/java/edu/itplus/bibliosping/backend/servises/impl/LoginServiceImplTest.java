package edu.itplus.bibliosping.backend.servises.impl;

import edu.itplus.bibliosping.backend.repository.UserDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

class LoginServiceImplTest {

    private LoginServiceImpl sut;

    private TestUserDAO testUserDAO;

    @BeforeEach
    void setUp() {
        sut = new LoginServiceImpl();
        testUserDAO = new TestUserDAO();

        ReflectionTestUtils.setField(sut,"userDAO",testUserDAO);
        ReflectionTestUtils.setField(sut,"passwordHasher",new TestPasswordDAO());
    }

    @Test
    void login() {
        boolean loginret = sut.login(TestUserDAO.nonDbUser);
        assertThat(loginret).isTrue();
        assertThat(testUserDAO.getLastSerachUser()).isEqualTo(TestUserDAO.nonDbUser.getUsername());
    }

    @Test
    void register() {
        //Arrange

        //Act

        //Assert
    }
}