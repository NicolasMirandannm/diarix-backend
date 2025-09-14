package nicksolutions.contracts.domain.auth;

import nicksolutions.core.crud.BaseRepository;

import java.util.Optional;

public interface ContractManagerRepository extends BaseRepository<ContractManager> {
  boolean existsByEmail(String email);
  Optional<ContractManager> findByEmail(String email);
}
