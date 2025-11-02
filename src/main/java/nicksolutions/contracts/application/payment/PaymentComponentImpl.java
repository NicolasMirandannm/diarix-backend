package nicksolutions.contracts.application.payment;

import nicksolutions.contracts.application.payment.dto.PaymentDto;
import nicksolutions.contracts.domain.payment.Payment;
import nicksolutions.contracts.domain.payment.service.PaymentService;
import nicksolutions.core.crud.AbstractApplicationComponent;
import nicksolutions.core.crud.ApplicationMapper;
import org.springframework.stereotype.Component;

@Component
public class PaymentComponentImpl extends AbstractApplicationComponent<PaymentDto, Payment, PaymentService> implements PaymentComponent {

  protected PaymentComponentImpl(ApplicationMapper<PaymentDto, Payment> mapper, PaymentService service) {
    super(mapper, service);
  }

}
