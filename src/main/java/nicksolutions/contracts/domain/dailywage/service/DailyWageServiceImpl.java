package nicksolutions.contracts.domain.dailywage.service;

import nicksolutions.contracts.configuration.DailyWageException;
import nicksolutions.contracts.domain.dailywage.DailyWage;
import nicksolutions.contracts.domain.dailywage.DailyWageRepository;
import nicksolutions.core.crud.BaseAbstractServiceImpl;
import nicksolutions.core.shared.PaymentStatus;
import org.springframework.stereotype.Service;

@Service
public class DailyWageServiceImpl extends BaseAbstractServiceImpl<DailyWage, DailyWageRepository> implements DailyWageService {

  public DailyWageServiceImpl(DailyWageRepository repository) {
    super(repository);
  }

  @Override
  public DailyWage save(DailyWage entity) {
    if (entity.isNew()) {
      entity.setPaymentStatus(PaymentStatus.NAO_PAGO);
    }

    return super.save(entity);
  }
}
