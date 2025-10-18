package nicksolutions.contracts.domain.enterprise.service;

import nicksolutions.contracts.domain.enterprise.Enterprise;
import nicksolutions.contracts.domain.enterprise.EnterpriseRepository;
import nicksolutions.core.shared.StatusRegister;
import nicksolutions.core.crud.BaseAbstractServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class EnterpriseServiceImpl extends BaseAbstractServiceImpl<Enterprise, EnterpriseRepository> implements EnterpriseService {

  public EnterpriseServiceImpl(EnterpriseRepository repository) {
    super(repository);
  }

  @Override
  public Enterprise save(Enterprise entity) {
    if (entity.isNew()) {
      entity.setStatus(StatusRegister.ATIVO);
    }
    return super.save(entity);
  }
}