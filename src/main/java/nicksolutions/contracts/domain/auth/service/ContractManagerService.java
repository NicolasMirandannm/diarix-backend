package nicksolutions.contracts.domain.auth.service;

import nicksolutions.contracts.domain.auth.ContractManager;
import nicksolutions.core.crud.BaseService;

import java.util.Optional;

public interface ContractManagerService extends BaseService<ContractManager> {
  boolean existsByEmail(String email);
  Optional<ContractManager> findByEmail(String email);
}
