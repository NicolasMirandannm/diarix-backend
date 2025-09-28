package nicksolutions.contracts.rest;

import nicksolutions.contracts.application.enterprise.EnterpriseComponent;
import nicksolutions.contracts.application.enterprise.dto.EnterpriseDto;
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
  public Page<EnterpriseDto> findAllEnterprises(@PageableDefault Pageable pageable) {
    return enterpriseComponent.list(pageable.getPageNumber(), pageable.getPageSize());
  }

  @GetMapping("/{id}")
  public EnterpriseDto findEnterpriseById(@PathVariable String id) {
    return enterpriseComponent.getById(id);
  }

  @PostMapping
  public EnterpriseDto createEnterprise(@RequestBody EnterpriseDto enterpriseDto) {
    return enterpriseComponent.create(enterpriseDto);
  }

  @PutMapping("/{id}")
  public EnterpriseDto updateEnterprise(@PathVariable String id, @RequestBody EnterpriseDto enterpriseDto) {
    return enterpriseComponent.update(id, enterpriseDto);
  }

  @DeleteMapping("/{id}")
  public void deleteEnterprise(@PathVariable String id) {
    enterpriseComponent.delete(id);
  }
}
