package task.aston.banking_app.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import task.aston.banking_app.exceptions.InvalidPinException;
import task.aston.banking_app.exceptions.WrongPinException;
import task.aston.banking_app.pojo.dto.WithdrawRequest;
import task.aston.banking_app.pojo.entity.Account;

@Service
@AllArgsConstructor
public class SecurityService {
    PasswordEncoder encoder;

    public void validatePin(WithdrawRequest withdrawRequest, Account account) {
        String pin = validatePinFormat(withdrawRequest.getPin());
        if (!encoder.matches(pin, account.getPin())) {
            throw new WrongPinException();
        }
    }

    public String validatePinFormat(String pin) {
        if (!pin.matches("\\d{4}")) {
            throw new InvalidPinException();
        }
        return pin;
    }

    public String encodePin(String pin) {
        return encoder.encode(pin);
    }
}
