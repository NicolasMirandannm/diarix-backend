package nicksolutions.contracts.domain.auth;

import nicksolutions.core.crud.BaseRepository;

import java.util.Optional;

public interface ManagerRepository extends BaseRepository<Manager> {
  boolean existsByEmail(String email);
  Optional<Manager> findByEmail(String email);
}
