package edu.itplus.bibliosping.backend.utils;

import edu.itplus.bibliosping.backend.utils.impl.PasswordEncrypterSha256;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class PasswordEncrypterSha256Test {
    @ParameterizedTest
    @CsvSource({
            "password123,18b09ff8-9718-49ea-9588-c1a65ff10d8e,7DA449FC9ECCB29FF0312FFF1F88C125B38B2F1EE5C37DD52B52A1EE9593883B",
            "password,7eeb0665-ae04-43f3-bf9b-b7d8eac9cf0e,5920607674B1E10DDC63EA3CAA59BDF66F1402D288DCB1B7444A06AB69A0CA4E",
            "abcd, e4c14e48-4875-4ef0-ba76-a165773c141e, 11D1B1957334D912546B172201B43EEC6867D3E0018C5BDB138693BC48426358",
            "hello,12345678-aaaa-bbbb-cccc-123456789abc,D40B8A34E4785FAFC978D746F1CBDB61C276704A31FB822AA9ACF831A261450D",
            "password,ebddeb7b-32fb-4f51-9743-190bedfc0e7f,BC50CD2F0BC9EE55739E2EC419FF45C6F7E160A64A23136A9A171F9AD7B19CF6",
            "kiskutya,4491e5b4-ee14-45d2-a532-c4a276cd69de,3F162714CA3FD2FB0FC88C7C5121562E0A194E074B33067EA90E6B488E56702D",
    })
    void hashPassword_passwordIsHashed_hashedPasswordIsCorrect(String password, String salt, String expectedHash) {
        // Arrange
        PasswordEncrypterSha256 systemUnderTest = new PasswordEncrypterSha256();

        // Act
        String hashedPassword = systemUnderTest.hashPassword(password, salt);

        // Assert
        assertThat(hashedPassword).isEqualTo(expectedHash);
    }
}