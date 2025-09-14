package nicksolutions.contracts.domain.auth;

import nicksolutions.core.crud.BaseAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractManagerServiceImpl extends BaseAbstractServiceImpl<ContractManager, ContractManagerRepository> implements ContractManagerService {

  @Autowired
  public ContractManagerServiceImpl(ContractManagerRepository repository) {
    super(repository);
  }
}
