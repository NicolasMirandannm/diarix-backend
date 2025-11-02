package nicksolutions.contracts.application.enterprise;

import nicksolutions.contracts.application.enterprise.dto.EnterpriseDto;
import nicksolutions.contracts.domain.enterprise.Enterprise;
import nicksolutions.contracts.domain.enterprise.service.EnterpriseService;
import nicksolutions.core.crud.AbstractApplicationComponent;
import nicksolutions.core.crud.ApplicationMapper;
import nicksolutions.core.shared.StatusRegister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class EnterpriseComponentImpl extends AbstractApplicationComponent<EnterpriseDto, Enterprise, EnterpriseService> implements EnterpriseComponent {

  protected EnterpriseComponentImpl(ApplicationMapper<EnterpriseDto, Enterprise> mapper, EnterpriseService service) {
    super(mapper, service);
  }

  @Override
  public Page<EnterpriseDto> findWithFilters(String name, String cnpj, String ownerName, StatusRegister status, Pageable pageable) {
    return service.findWithFilters(name, cnpj, ownerName, status, pageable)
        .map(mapper::toDto);
  }
}
