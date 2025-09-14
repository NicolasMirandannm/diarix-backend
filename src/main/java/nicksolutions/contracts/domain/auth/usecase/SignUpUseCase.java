package nicksolutions.contracts.domain.auth.usecase;

import nicksolutions.core.exceptions.CustomHttpException;
import nicksolutions.contracts.domain.auth.ContractManager;
import nicksolutions.contracts.domain.auth.service.ContractManagerService;
import nicksolutions.contracts.domain.auth.service.TokenJwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SignUpUseCase {

  private final ContractManagerService contractManagerService;
  private final PasswordEncryptionUseCase passwordEncryptionUseCase;
  private final TokenJwtService tokenJwtService;

  @Autowired
  public SignUpUseCase(ContractManagerService contractManagerService, PasswordEncryptionUseCase passwordEncryptionUseCase, TokenJwtService tokenJwtService) {
    this.contractManagerService = contractManagerService;
    this.passwordEncryptionUseCase = passwordEncryptionUseCase;
    this.tokenJwtService = tokenJwtService;
  }

  public String execute(ContractManager contractManager) {
    if (contractManagerService.existsByEmail(contractManager.getEmail())) {
      throw CustomHttpException.badRequest("O email já está em uso.");
    }
    contractManager.setPassword(passwordEncryptionUseCase.encryptPassword(contractManager.getPassword()));
    return tokenJwtService.generateCustomerJwtToken(contractManagerService.save(contractManager));
  }
}
