package nicksolutions.contracts.rest;

import nicksolutions.contracts.application.enterprise.EnterpriseComponent;
import nicksolutions.contracts.application.enterprise.dto.EnterpriseDto;
import nicksolutions.core.shared.StatusRegister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enterprises")
public class EnterpriseController {

  private final EnterpriseComponent enterpriseComponent;

  public EnterpriseController(EnterpriseComponent enterpriseComponent) {
    this.enterpriseComponent = enterpriseComponent;
  }

  @GetMapping
  public Page<EnterpriseDto> findAll(
      @RequestParam(required = false) String name,
      @RequestParam(required = false) String cnpj,
      @RequestParam(required = false) String ownerName,
      @RequestParam(required = false) StatusRegister status,
      @PageableDefault Pageable pageable) {

    return enterpriseComponent.findWithFilters(name, cnpj, ownerName, status, pageable);
  }

  @GetMapping("/{id}")
  public EnterpriseDto findById(@PathVariable String id) {
    return enterpriseComponent.getById(id);
  }

  @PostMapping
  public EnterpriseDto create(@RequestBody EnterpriseDto enterpriseDto) {
    return enterpriseComponent.create(enterpriseDto);
  }

  @PutMapping("/{id}")
  public EnterpriseDto update(@PathVariable String id, @RequestBody EnterpriseDto enterpriseDto) {
    return enterpriseComponent.update(id, enterpriseDto);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable String id) {
    enterpriseComponent.delete(id);
  }
}
