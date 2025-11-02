package nicksolutions.contracts.domain.payment.service;

import nicksolutions.contracts.domain.dailywage.DailyWage;
import nicksolutions.contracts.domain.dailywage.service.DailyWageService;
import nicksolutions.contracts.domain.payment.Payment;
import nicksolutions.contracts.domain.payment.PaymentRepository;
import nicksolutions.contracts.domain.payment.usecase.PaymentDailyWageCalculator;
import nicksolutions.core.crud.BaseAbstractServiceImpl;
import nicksolutions.core.shared.PaymentStatus;
import org.springframework.stereotype.Service;

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
    payment.setDailyWages(dailyWageService.updatePaymentStatus(payment.getDailyWageIds(), PaymentStatus.PAGO));
    payment.setValue(paymentDailyWageCalculator.compute(payment.getDailyWageIds()));
    return super.save(payment);
  }

  @Override
  public void deleteById(String id) {
    Payment payment = findByIdOrThrow(id);
    dailyWageService.updatePaymentStatus(payment.getDailyWageIds(), PaymentStatus.NAO_PAGO);
    super.deleteById(id);
  }
}