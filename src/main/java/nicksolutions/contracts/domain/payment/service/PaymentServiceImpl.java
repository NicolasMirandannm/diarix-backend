package nicksolutions.contracts.domain.payment.service;

import nicksolutions.contracts.domain.dailywage.DailyWage;
import nicksolutions.contracts.domain.dailywage.service.DailyWageService;
import nicksolutions.contracts.domain.payment.Payment;
import nicksolutions.contracts.domain.payment.PaymentRepository;
import nicksolutions.contracts.domain.payment.usecase.PaymentDailyWageCalculator;
import nicksolutions.core.crud.BaseAbstractServiceImpl;
import nicksolutions.core.shared.PaymentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;

import static java.util.Objects.isNull;

@Service
public class PaymentServiceImpl extends BaseAbstractServiceImpl<Payment, PaymentRepository> implements PaymentService {

  private final DailyWageService dailyWageService;
  private final PaymentDailyWageCalculator paymentDailyWageCalculator;

  public PaymentServiceImpl(PaymentRepository repository, DailyWageService dailyWageService, PaymentDailyWageCalculator paymentDailyWageCalculator) {
    super(repository);
    this.dailyWageService = dailyWageService;
    this.paymentDailyWageCalculator = paymentDailyWageCalculator;
  }

  @Override
  public Payment save(Payment payment) {
    payment.setValue(paymentDailyWageCalculator.compute(payment.getDailyWageIds()));
    payment = super.save(payment);
    payment.setDailyWages(dailyWageService.updatePaymentStatus(payment, PaymentStatus.PAGO));
    return payment;
  }

  @Override
  public void deleteById(String id) {
    Payment payment = findByIdOrThrow(id);
    dailyWageService.updatePaymentStatus(payment, PaymentStatus.NAO_PAGO);
    super.deleteById(id);
  }

  @Override
  public Page<Payment> findByFilters(String paymentId, LocalDate startDate, LocalDate endDate, Pageable pageable) {
    return isNull(paymentId)
        ? repository.findByDateBetween(startDate, endDate, pageable)
        : new PageImpl<>(Collections.singletonList(findByIdOrThrow(paymentId)));
  }
}