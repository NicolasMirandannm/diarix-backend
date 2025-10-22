package nicksolutions.contracts.domain.auth.usecase;

import nicksolutions.contracts.domain.auth.Manager;
import nicksolutions.contracts.domain.auth.service.ManagerService;
import nicksolutions.contracts.domain.auth.service.TokenJwtService;
import nicksolutions.core.exceptions.CustomHttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SignInUseCase {

  private final ManagerService managerService;
  private final PasswordEncryptionUseCase passwordEncryptionUseCase;
  private final TokenJwtService tokenJwtService;

  @Autowired
  public SignInUseCase(ManagerService managerService, PasswordEncryptionUseCase passwordEncryptionUseCase, TokenJwtService tokenJwtService) {
    this.managerService = managerService;
    this.passwordEncryptionUseCase = passwordEncryptionUseCase;
    this.tokenJwtService = tokenJwtService;
  }

  public String execute(String email, String password) {
    Optional<Manager> manager = managerService.findByEmail(email);

    if (manager.isEmpty() || !passwordEncryptionUseCase.checkPassword(password, manager.get().getPassword())) {
      throw CustomHttpException.forbidden("Credenciais inválidas.");
    }
    return tokenJwtService.generateCustomerJwtToken(manager.get());
  }
}
