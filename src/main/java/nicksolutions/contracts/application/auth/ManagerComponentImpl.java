package nicksolutions.contracts.application.auth;

import nicksolutions.contracts.application.auth.dto.ManagerDto;
import nicksolutions.contracts.application.auth.dto.LoginDto;
import nicksolutions.contracts.application.auth.dto.TokenDto;
import nicksolutions.contracts.domain.auth.Manager;
import nicksolutions.contracts.domain.auth.service.ManagerService;
import nicksolutions.contracts.domain.auth.service.TokenJwtService;
import nicksolutions.contracts.domain.auth.usecase.SignInUseCase;
import nicksolutions.contracts.domain.auth.usecase.SignUpUseCase;
import nicksolutions.core.crud.AbstractApplicationComponent;
import org.springframework.stereotype.Component;

@Component
public class ManagerComponentImpl extends AbstractApplicationComponent<ManagerDto, Manager, ManagerService> implements ManagerComponent {

  private final SignUpUseCase signUpUseCase;
  private final SignInUseCase signInUseCase;
  private final TokenJwtService tokenJwtService;

  protected ManagerComponentImpl(ManagerMapper mapper,
                                         ManagerService service,
                                         SignUpUseCase signUpUseCase,
                                         SignInUseCase signInUseCase,
                                         TokenJwtService tokenJwtService) {
    super(mapper, service);
    this.signUpUseCase = signUpUseCase;
    this.signInUseCase = signInUseCase;
    this.tokenJwtService = tokenJwtService;
  }

  public TokenDto signUp(ManagerDto ManagerDto) {
    String token = signUpUseCase.execute(mapper.toEntity(ManagerDto));
    return TokenDto.builder()
        .token(token)
        .managerId(tokenJwtService.getManagerIdFromToken(token))
        .build();
  }

  @Override
  public TokenDto signIn(LoginDto loginDto) {
    String token = signInUseCase.execute(loginDto.getEmail(), loginDto.getPassword());
    return TokenDto.builder()
        .token(token)
        .managerId(tokenJwtService.getManagerIdFromToken(token))
        .build();
  }
}
