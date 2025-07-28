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
        dbUser.setPassword(TestPasswordEncrypter.hashedPassword);
        dbUser.setUsername("Pistike");
        dbUser.setUuid(TestPasswordEncrypter.salt);
        dbUser.setId(1L);

        testUserDAO = mock(UserDAO.class);
        testpasswordEncrypter = mock(PasswordEncrypter.class);
        when(testpasswordEncrypter.hashPassword(TestPasswordEncrypter.password,TestPasswordEncrypter.salt))
                .thenReturn(TestPasswordEncrypter.hashedPassword);

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
        // Arrange
        User newUser = new User();
        newUser.setUsername("ujuser");
        newUser.setPassword("titkosjelszo");
        newUser.setUuid("egyedi_salt");

        String hashed = "hash_titkosjelszo";

        when(testpasswordEncrypter.hashPassword("titkosjelszo", "egyedi_salt"))
                .thenReturn(hashed);

        // Act
        sut.register(newUser);

        // Assert
        assertThat(newUser.getPassword()).isEqualTo(hashed); // jelszo hash-elve lett
        verify(testpasswordEncrypter, times(1)).hashPassword("titkosjelszo", "egyedi_salt");
        verify(testUserDAO, times(1)).create(newUser);
    }

}