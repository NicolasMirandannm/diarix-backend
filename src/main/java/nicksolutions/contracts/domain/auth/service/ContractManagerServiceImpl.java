package nicksolutions.contracts.domain.auth.service;

import nicksolutions.contracts.domain.auth.ContractManager;
import nicksolutions.contracts.domain.auth.ContractManagerRepository;
import nicksolutions.core.crud.BaseAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContractManagerServiceImpl extends BaseAbstractServiceImpl<ContractManager, ContractManagerRepository> implements ContractManagerService {

  @Autowired
  public ContractManagerServiceImpl(ContractManagerRepository repository) {
    super(repository);
  }

  @Override
  public boolean existsByEmail(String email) {
    return repository.existsByEmail(email);
  }

  @Override
  public Optional<ContractManager> findByEmail(String email) {
    return repository.findByEmail(email);
  }
}
