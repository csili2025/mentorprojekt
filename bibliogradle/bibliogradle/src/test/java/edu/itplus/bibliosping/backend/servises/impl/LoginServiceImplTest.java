package edu.itplus.bibliosping.backend.servises.impl;

import edu.itplus.bibliosping.backend.model.User;
import edu.itplus.bibliosping.backend.repository.UserDAO;
import edu.itplus.bibliosping.backend.utils.PasswordEncrypter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class LoginServiceImplTest {

    private LoginServiceImpl sut;
    private User nonDbUser;
    private User dbUser;
    private PasswordEncrypter testpasswordEncrypter;
    private UserDAO testUserDAO;

    @BeforeEach
    void setUp() {
        sut = new LoginServiceImpl();
        nonDbUser = new User();
        nonDbUser.setPassword(TestPasswordEncrypter.password);
        nonDbUser.setUsername("Pistike");
        nonDbUser.setUuid(TestPasswordEncrypter.salt);
        nonDbUser.setId(1L);

        dbUser = new User();
        dbUser.setPassword(TestPasswordEncrypter.password);
        dbUser.setUsername("Pistike");
        dbUser.setUuid(TestPasswordEncrypter.salt);
        dbUser.setId(1L);

        testUserDAO = mock(UserDAO.class);
        testpasswordEncrypter = mock(PasswordEncrypter.class);
        when(testpasswordEncrypter.hashPassword(nonDbUser.getPassword(),nonDbUser.getUuid()))
                .thenReturn(dbUser.getPassword());

        ReflectionTestUtils.setField(sut,"userDAO",testUserDAO);
        when(testUserDAO.findbyUsername(nonDbUser.getUsername())).thenReturn(dbUser);

        ReflectionTestUtils.setField(sut,"passwordHasher", testpasswordEncrypter);
    }

    @Test
    void login() {
        boolean loginret = sut.login(nonDbUser);
        assertThat(loginret).isTrue();
        verify(testUserDAO, times(1)).findbyUsername(nonDbUser.getUsername());
    }

    @Test
    void register() {
        //Arrange

        //Act

        //Assert
    }
}