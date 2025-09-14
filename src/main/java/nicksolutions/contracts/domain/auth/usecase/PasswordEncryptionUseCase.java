package nicksolutions.contracts.domain.auth.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncryptionUseCase {
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public PasswordEncryptionUseCase(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  public String encryptPassword(String password) {
    return passwordEncoder.encode(password);
  }

  public boolean checkPassword(String plainPassword, String encryptedPassword) {
    return passwordEncoder.matches(plainPassword, encryptedPassword);
  }
}
