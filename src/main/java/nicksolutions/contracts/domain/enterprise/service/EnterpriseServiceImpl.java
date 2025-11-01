package nicksolutions.contracts.domain.enterprise.service;

import nicksolutions.contracts.domain.enterprise.Enterprise;
import nicksolutions.contracts.domain.enterprise.EnterpriseRepository;
import nicksolutions.contracts.domain.enterprise.service.filters.EnterpriseSpecifications;
import nicksolutions.core.shared.StatusRegister;
import nicksolutions.core.crud.BaseAbstractServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

  @Override
  public Page<Enterprise> findWithFilters(String name, String cnpj, String ownerName, StatusRegister status, Pageable pageable) {

    Specification<Enterprise> spec = EnterpriseSpecifications.withFilters(
        name, cnpj, ownerName, status
    );

    return repository.findAll(spec, pageable);
  }
}