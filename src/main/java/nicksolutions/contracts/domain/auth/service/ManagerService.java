package nicksolutions.contracts.domain.auth.service;

import nicksolutions.contracts.domain.auth.Manager;
import nicksolutions.core.crud.BaseService;

import java.util.Optional;

public interface ManagerService extends BaseService<Manager> {
  boolean existsByEmail(String email);
  Optional<Manager> findByEmail(String email);
}
