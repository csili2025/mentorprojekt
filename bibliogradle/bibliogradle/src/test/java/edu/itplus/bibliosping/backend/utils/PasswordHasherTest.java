package edu.itplus.bibliosping.backend.utils;

import edu.itplus.bibliosping.backend.utils.impl.PasswordEncrypterSha256;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class PasswordHasherTest {

    @Test
    void hashPassword() {
    }

    @ParameterizedTest
    @ValueSource(strings = {"hello","password12345","test@123"})
    void hashPassword_givenDifferentInput_shouldReturnDifferentHash(String input) {
        // Arrange
        PasswordEncrypterSha256 sut = new PasswordEncrypterSha256();
        String password = "abcde";
        String salt = "44a3415d-65f1-44c8-aa7f-c62ed4c50675";

        // Act
        String hash = sut.hashPassword(password, salt);

        // Assert
        String expectedHash = "D02D1748B55B82DF438C1C6A933D63AED8FDE20CEF6C6CDA6D1879623E8DE81B";
        assertThat(hash).isEqualTo(expectedHash);
    }
    @ParameterizedTest
    @CsvSource({
            "abcde,44a3415d-65f1-44c8-aa7f-c62ed4c50675,D02D1748B55B82DF438C1C6A933D63AED8FDE20CEF6C6CDA6D1879623E8DE81B",
            "hello, 12345678-aaaa-bbbb-cccc-123456789abc,D40B8A34E4785FAFC978D746F1CBDB61C276704A31FB822AA9ACF831A261450D",
            "mypassword,ffffffff-eeee-dddd-cccc-bbbbbbbbbbbb,FF511CB2A235009B4EA505A285EFB258D97B945971B3C8C9BAD162B505D76502",
            "kiskutya, 4491e5b4-ee14-45d2-a532-c4a276cd69de, 3F162714CA3FD2FB0FC88C7C5121562E0A194E074B33067EA90E6B488E56702D",
            "test, bd207845-bdf3-45f7-af8e-b2e2763ddccc, C9834A8F064DF1CA8CB324E120D4192FE34A9858628EF0AF6AF0F4ADA531215B"
    })
    void hashPassword_givenPasswordAndSalt_shouldReturnExpectedHash(String password, String salt, String expectedHash) {
        // Arrange
        PasswordEncrypterSha256 sut = new PasswordEncrypterSha256();
        // Act
        String actualHash = sut.hashPassword(password, salt);
        // Assert
        assertThat(actualHash).isEqualTo(expectedHash);
    }
}