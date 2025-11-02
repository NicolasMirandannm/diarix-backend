package nicksolutions.contracts.application.enterprise;

import nicksolutions.contracts.application.enterprise.dto.EnterpriseDto;
import nicksolutions.core.crud.ApplicationComponent;
import nicksolutions.core.shared.StatusRegister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EnterpriseComponent extends ApplicationComponent<EnterpriseDto> {

  Page<EnterpriseDto> findWithFilters(String name, String cnpj, String ownerName, StatusRegister status, Pageable pageable);
}
