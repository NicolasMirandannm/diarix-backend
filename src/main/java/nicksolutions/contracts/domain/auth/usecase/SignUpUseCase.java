package nicksolutions.contracts.domain.auth.usecase;

import nicksolutions.core.exceptions.CustomHttpException;
import nicksolutions.contracts.domain.auth.Manager;
import nicksolutions.contracts.domain.auth.service.ManagerService;
import nicksolutions.contracts.domain.auth.service.TokenJwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SignUpUseCase {

  private final ManagerService managerService;
  private final PasswordEncryptionUseCase passwordEncryptionUseCase;
  private final TokenJwtService tokenJwtService;

  @Autowired
  public SignUpUseCase(ManagerService managerService, PasswordEncryptionUseCase passwordEncryptionUseCase, TokenJwtService tokenJwtService) {
    this.managerService = managerService;
    this.passwordEncryptionUseCase = passwordEncryptionUseCase;
    this.tokenJwtService = tokenJwtService;
  }

  public String execute(Manager manager) {
    if (managerService.existsByEmail(manager.getEmail())) {
      throw CustomHttpException.badRequest("O email já está em uso.");
    }
    manager.setPassword(passwordEncryptionUseCase.encryptPassword(manager.getPassword()));
    return tokenJwtService.generateCustomerJwtToken(managerService.save(manager));
  }
}
