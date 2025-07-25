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
        String salt = "44a3415d-65f1-44c8-aa7f-c62ed4c50675";

        // Act
        String hash = sut.hashPassword(password, salt);

        // Assert
        String expectedHash = "D02D1748B55B82DF438C1C6A933D63AED8FDE20CEF6C6CDA6D1879623E8DE81B";
        assertThat(hash).isEqualTo(expectedHash);
    }
}