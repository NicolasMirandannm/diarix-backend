package nicksolutions.contracts.application.payment;

import nicksolutions.contracts.application.payment.dto.PaymentDto;
import nicksolutions.contracts.domain.payment.Payment;
import nicksolutions.contracts.domain.payment.service.PaymentService;
import nicksolutions.core.crud.AbstractApplicationComponent;
import nicksolutions.core.crud.ApplicationMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PaymentComponentImpl extends AbstractApplicationComponent<PaymentDto, Payment, PaymentService> implements PaymentComponent {

  protected PaymentComponentImpl(ApplicationMapper<PaymentDto, Payment> mapper, PaymentService service) {
    super(mapper, service);
  }

  @Override
  public Page<PaymentDto> findWithFilters(String paymentId, LocalDate startDate, LocalDate endDate, Pageable pageable) {
    return service.findByFilters(paymentId, startDate, endDate, pageable).map(mapper::toDto);
  }
}
