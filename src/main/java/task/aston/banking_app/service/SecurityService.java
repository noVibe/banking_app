package task.aston.banking_app.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import task.aston.banking_app.exceptions.WrongPinException;

@Service
@AllArgsConstructor
public class SecurityService {
    private PasswordEncoder encoder;

    public void verifyPin(String pin, String correct) {
        if (!encoder.matches(pin, correct)) {
            throw new WrongPinException();
        }
    }

    public String encodePin(String pin) {
        return encoder.encode(pin);
    }
}
