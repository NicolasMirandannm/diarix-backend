package nicksolutions.contracts.application.auth;

import nicksolutions.contracts.application.auth.dto.ManagerDto;
import nicksolutions.contracts.application.auth.dto.LoginDto;
import nicksolutions.contracts.application.auth.dto.TokenDto;
import nicksolutions.core.crud.ApplicationComponent;

public interface ManagerComponent extends ApplicationComponent<ManagerDto> {
  TokenDto signUp(ManagerDto ManagerDto);
  TokenDto signIn(LoginDto loginDto);
}
