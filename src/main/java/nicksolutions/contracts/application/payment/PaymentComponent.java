package nicksolutions.contracts.application.payment;

import nicksolutions.contracts.application.payment.dto.PaymentDto;
import nicksolutions.core.crud.ApplicationComponent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.File;
import java.time.LocalDate;

public interface PaymentComponent extends ApplicationComponent<PaymentDto> {
  Page<PaymentDto> findWithFilters(String paymentId, LocalDate startDate, LocalDate endDate, Pageable pageable);

  File generatePaymentStatementPdf(String paymentId);
}
