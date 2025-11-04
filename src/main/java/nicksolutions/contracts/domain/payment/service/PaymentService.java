package nicksolutions.contracts.domain.payment.service;

import nicksolutions.contracts.application.dailywage.dto.DailyWageDto;
import nicksolutions.contracts.domain.enterprise.Enterprise;
import nicksolutions.contracts.domain.payment.Payment;
import nicksolutions.core.crud.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface PaymentService extends BaseService<Payment> {
  Page<Payment> findByFilters(String paymentId, LocalDate startDate, LocalDate endDate, Pageable pageable);
}
