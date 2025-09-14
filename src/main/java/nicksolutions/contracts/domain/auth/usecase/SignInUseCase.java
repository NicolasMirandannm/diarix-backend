package nicksolutions.contracts.domain.auth.usecase;

import nicksolutions.contracts.domain.auth.ContractManager;
import nicksolutions.contracts.domain.auth.service.ContractManagerService;
import nicksolutions.contracts.domain.auth.service.TokenJwtService;
import nicksolutions.core.exceptions.CustomHttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SignInUseCase {

  private final ContractManagerService contractManagerService;
  private final PasswordEncryptionUseCase passwordEncryptionUseCase;
  private final TokenJwtService tokenJwtService;

  @Autowired
  public SignInUseCase(ContractManagerService contractManagerService, PasswordEncryptionUseCase passwordEncryptionUseCase, TokenJwtService tokenJwtService) {
    this.contractManagerService = contractManagerService;
    this.passwordEncryptionUseCase = passwordEncryptionUseCase;
    this.tokenJwtService = tokenJwtService;
  }

  public String execute(String email, String password) {
    Optional<ContractManager> contractManager = contractManagerService.findByEmail(email);

    if (contractManager.isEmpty() || !passwordEncryptionUseCase.checkPassword(password, contractManager.get().getPassword())) {
      throw CustomHttpException.forbidden("Credenciais inválidas.");
    }
    return tokenJwtService.generateCustomerJwtToken(contractManager.get());
  }
}
