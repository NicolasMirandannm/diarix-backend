package nicksolutions.contracts.application.auth;

import nicksolutions.contracts.application.auth.dto.ContractManagerDto;
import nicksolutions.contracts.application.auth.dto.LoginDto;
import nicksolutions.contracts.application.auth.dto.TokenDto;
import nicksolutions.contracts.domain.auth.ContractManager;
import nicksolutions.contracts.domain.auth.service.ContractManagerService;
import nicksolutions.contracts.domain.auth.service.TokenJwtService;
import nicksolutions.contracts.domain.auth.usecase.SignInUseCase;
import nicksolutions.contracts.domain.auth.usecase.SignUpUseCase;
import nicksolutions.core.crud.AbstractApplicationComponent;
import org.springframework.stereotype.Component;

@Component
public class ContractManagerComponentImpl extends AbstractApplicationComponent<ContractManagerDto, ContractManager, ContractManagerService> implements ContractManagerComponent {

  private final SignUpUseCase signUpUseCase;
  private final SignInUseCase signInUseCase;
  private final TokenJwtService tokenJwtService;

  protected ContractManagerComponentImpl(ContractManagerMapper mapper,
                                         ContractManagerService service,
                                         SignUpUseCase signUpUseCase,
                                         SignInUseCase signInUseCase,
                                         TokenJwtService tokenJwtService) {
    super(mapper, service);
    this.signUpUseCase = signUpUseCase;
    this.signInUseCase = signInUseCase;
    this.tokenJwtService = tokenJwtService;
  }

  public TokenDto signUp(ContractManagerDto contractManagerDto) {
    String token = signUpUseCase.execute(mapper.toEntity(contractManagerDto));
    return TokenDto.builder()
        .token(token)
        .contractManagerId(tokenJwtService.getContractManagerIdFromToken(token))
        .build();
  }

  @Override
  public TokenDto signIn(LoginDto loginDto) {
    String token = signInUseCase.execute(loginDto.getEmail(), loginDto.getPassword());
    return TokenDto.builder()
        .token(token)
        .contractManagerId(tokenJwtService.getContractManagerIdFromToken(token))
        .build();
  }
}
