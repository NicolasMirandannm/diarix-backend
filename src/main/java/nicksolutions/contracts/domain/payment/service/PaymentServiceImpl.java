package nicksolutions.contracts.domain.payment.service;

import nicksolutions.contracts.domain.payment.Payment;
import nicksolutions.contracts.domain.payment.PaymentRepository;
import nicksolutions.core.crud.BaseAbstractServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl extends BaseAbstractServiceImpl<Payment, PaymentRepository> implements PaymentService {

  public PaymentServiceImpl(PaymentRepository repository) {
    super(repository);
  }
}