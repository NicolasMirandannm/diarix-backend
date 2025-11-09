package nicksolutions.contracts.domain.auth.service;

import nicksolutions.contracts.domain.auth.Manager;
import nicksolutions.contracts.domain.auth.ManagerRepository;
import nicksolutions.core.crud.BaseAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ManagerServiceImpl extends BaseAbstractServiceImpl<Manager, ManagerRepository> implements ManagerService {

  @Autowired
  public ManagerServiceImpl(ManagerRepository repository) {
    super(repository);
  }

  @Override
  public boolean existsByEmail(String email) {
    return repository.existsByEmail(email);
  }

  @Override
  public Optional<Manager> findByEmail(String email) {
    return repository.findByEmail(email);
  }

  @Override
  public boolean existsById(String managerId) {
    return repository.existsById(managerId);
  }
}
