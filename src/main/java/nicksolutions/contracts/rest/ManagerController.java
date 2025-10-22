package nicksolutions.contracts.rest;

import nicksolutions.contracts.application.auth.ManagerComponent;
import nicksolutions.contracts.application.auth.dto.ManagerDto;
import nicksolutions.contracts.application.auth.dto.LoginDto;
import nicksolutions.contracts.application.auth.dto.TokenDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contract-managers")
public class ManagerController {

  private final ManagerComponent managerComponent;

  public ManagerController(ManagerComponent managerComponent) {
    this.managerComponent = managerComponent;
  }

  @GetMapping("/{id}")
  public ResponseEntity<ManagerDto> getById(@PathVariable("id") String id) {
    ManagerDto ManagerDto = managerComponent.getById(id);
    return ResponseEntity.ok(ManagerDto);
  }

  @PostMapping("/sign-up")
  public ResponseEntity<TokenDto> signUp(@RequestBody ManagerDto ManagerDto) {
    return ResponseEntity.ok(managerComponent.signUp(ManagerDto));
  }

  @PostMapping("/sign-in")
  public ResponseEntity<TokenDto> signIn(@RequestBody LoginDto loginDto) {
    return ResponseEntity.ok(managerComponent.signIn(loginDto));
  }
}
