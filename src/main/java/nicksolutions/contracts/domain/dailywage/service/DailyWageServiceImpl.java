package nicksolutions.contracts.domain.dailywage.service;

import nicksolutions.contracts.domain.dailywage.DailyWage;
import nicksolutions.contracts.domain.dailywage.DailyWageRepository;
import nicksolutions.contracts.domain.dailywage.service.filters.DailyWageSpecifications;
import nicksolutions.contracts.domain.daylaborer.service.DayLaborerService;
import nicksolutions.contracts.domain.enterprise.service.EnterpriseService;
import nicksolutions.contracts.domain.payment.Payment;
import nicksolutions.core.crud.BaseAbstractServiceImpl;
import nicksolutions.core.shared.PaymentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static java.util.Objects.isNull;

@Service
public class DailyWageServiceImpl extends BaseAbstractServiceImpl<DailyWage, DailyWageRepository> implements DailyWageService {

  private final DayLaborerService dayLaborerService;
  private final EnterpriseService enterpriseService;

  public DailyWageServiceImpl(DailyWageRepository repository, DayLaborerService dayLaborerService, EnterpriseService enterpriseService) {
    super(repository);
    this.dayLaborerService = dayLaborerService;
    this.enterpriseService = enterpriseService;
  }

  @Override
  public DailyWage save(DailyWage entity) {
    entity.setEnterprise(enterpriseService.findByIdOrThrow(entity.getEnterprise().getId()));
    entity.setDayLaborer(dayLaborerService.findByIdOrThrow(entity.getDayLaborer().getId()));

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

  @Override
  public List<DailyWage> findByIds(List<String> ids) {
    return repository.findAllByIdIn(ids);
  }

  @Override
  public List<DailyWage> updatePaymentStatus(Payment payment, PaymentStatus paymentStatus) {
    List<DailyWage> dailyWages = findByIds(payment.getDailyWageIds());
    dailyWages.forEach(dailyWage -> {
      dailyWage.setPaymentStatus(paymentStatus);
      dailyWage.setPayment(paymentStatus == PaymentStatus.PAGO ? payment : null);
    });
    return repository.saveAll(dailyWages);
  }

  @Override
  public List<DailyWage> findByDayLaborerIdAndFilters(String dayLaborerId, LocalDate startDate, LocalDate endDate, PaymentStatus paymentStatus, String enterpriseId) {
    return isNull(enterpriseId)
        ? repository.findByDayLaborerIdAndWorkDateBetweenAndPaymentStatus(dayLaborerId, startDate, endDate, paymentStatus)
        : repository.findByDayLaborerIdAndEnterpriseIdAndWorkDateBetweenAndPaymentStatus(dayLaborerId, enterpriseId, startDate, endDate, paymentStatus);
  }
}