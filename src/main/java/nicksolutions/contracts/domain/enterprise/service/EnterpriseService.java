package nicksolutions.contracts.domain.enterprise.service;

import nicksolutions.contracts.domain.enterprise.Enterprise;
import nicksolutions.core.crud.BaseService;
import nicksolutions.core.shared.StatusRegister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EnterpriseService extends BaseService<Enterprise> {

  Page<Enterprise> findWithFilters(String name, String cnpj, String ownerName, StatusRegister status, Pageable pageable);
}
