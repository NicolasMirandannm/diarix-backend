package nicksolutions.contracts.application.auth;

import nicksolutions.contracts.application.auth.dto.ContractManagerDto;
import nicksolutions.contracts.application.auth.dto.LoginDto;
import nicksolutions.contracts.application.auth.dto.TokenDto;
import nicksolutions.contracts.domain.auth.ContractManager;
import nicksolutions.core.crud.ApplicationComponent;

public interface ContractManagerComponent extends ApplicationComponent<ContractManagerDto, ContractManager> {
  TokenDto signUp(ContractManagerDto contractManagerDto);
  TokenDto signIn(LoginDto loginDto);
}
