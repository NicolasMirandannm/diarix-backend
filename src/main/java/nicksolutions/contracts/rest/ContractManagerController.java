package nicksolutions.contracts.rest;

import nicksolutions.contracts.application.auth.ContractManagerComponent;
import nicksolutions.contracts.application.auth.dto.ContractManagerDto;
import nicksolutions.contracts.application.auth.dto.LoginDto;
import nicksolutions.contracts.application.auth.dto.TokenDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contract-managers")
public class ContractManagerController {

  private final ContractManagerComponent contractManagerComponent;

  public ContractManagerController(ContractManagerComponent contractManagerComponent) {
    this.contractManagerComponent = contractManagerComponent;
  }

  @GetMapping("/{id}")
  public ResponseEntity<ContractManagerDto> getById(@PathVariable("id") String id) {
    ContractManagerDto contractManagerDto = contractManagerComponent.getById(id);
    return ResponseEntity.ok(contractManagerDto);
  }

  @PostMapping("/sign-up")
  public ResponseEntity<TokenDto> signUp(@RequestBody ContractManagerDto contractManagerDto) {
    return ResponseEntity.ok(contractManagerComponent.signUp(contractManagerDto));
  }

  @PostMapping("/sign-in")
  public ResponseEntity<TokenDto> signIn(@RequestBody LoginDto loginDto) {
    return ResponseEntity.ok(contractManagerComponent.signIn(loginDto));
  }
}
