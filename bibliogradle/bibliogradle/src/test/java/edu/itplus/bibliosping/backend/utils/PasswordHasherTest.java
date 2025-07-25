package edu.itplus.bibliosping.backend.utils;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

class PasswordHasherTest {

    @Test
    void hashPassword() {
    }

    @Test
    void hashPassword_givenDifferentInput_shouldReturnDifferentHash() {
        // Arrange
        PasswordHasher sut = new PasswordHasher();
        String password = "abcde";
        String salt = UUID.randomUUID().toString();

        // Act
        String hash = sut.hashPassword(password, salt);

        // Assert
        String expectedHash = "123456";
        assertThat(hash).isEqualTo(expectedHash);
    }
}