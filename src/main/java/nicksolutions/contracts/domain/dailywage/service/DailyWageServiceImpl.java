package nicksolutions.contracts.domain.dailywage.service;

import nicksolutions.contracts.domain.dailywage.DailyWage;
import nicksolutions.contracts.domain.dailywage.DailyWageRepository;
import nicksolutions.core.crud.BaseAbstractServiceImpl;
import nicksolutions.core.shared.PaymentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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

  @Override
  public Page<DailyWage> findWithFilters(String dayLaborerName, String enterpriseName,
                                         LocalDate workDate, PaymentStatus status, Pageable pageable) {

    Specification<DailyWage> spec = DailyWageSpecifications.withFilters(
        dayLaborerName, enterpriseName, workDate, status
    );

    return repository.findAll(spec, pageable);
  }
}