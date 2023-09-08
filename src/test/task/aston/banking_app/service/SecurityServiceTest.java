package task.aston.banking_app.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import task.aston.banking_app.exceptions.WrongPinException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SecurityServiceTest {
    @Mock
    PasswordEncoder encoder;
    @InjectMocks
    SecurityService out;
    static String CORRECT_PIN = "5555";
    static String WRONG_PIN = "3333";


    @Test
    void verifyPin() {
        when(encoder.matches(WRONG_PIN, CORRECT_PIN))
                .thenReturn(false);

        assertThrows(WrongPinException.class, () -> out.verifyPin(WRONG_PIN, CORRECT_PIN));
    }


    @Test
    void encodePin() {
        String result = "encoded";
        when(encoder.encode(CORRECT_PIN))
                .thenReturn(result);
        assertEquals(out.encodePin(CORRECT_PIN), result);
    }
}