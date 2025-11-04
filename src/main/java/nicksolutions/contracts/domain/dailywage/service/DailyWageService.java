package nicksolutions.contracts.domain.dailywage.service;

import nicksolutions.contracts.domain.dailywage.DailyWage;
import nicksolutions.contracts.domain.payment.Payment;
import nicksolutions.core.crud.BaseService;
import nicksolutions.core.shared.PaymentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface DailyWageService extends BaseService<DailyWage> {

  Page<DailyWage> findWithFilters(String dayLaborerName, String enterpriseName,
                                  LocalDate workDate, PaymentStatus status, Pageable pageable);

  List<DailyWage> findByIds(List<String> ids);

  List<DailyWage> updatePaymentStatus(Payment payment, PaymentStatus paymentStatus);

  List<DailyWage> findByDayLaborerIdAndFilters(String dayLaborerId, LocalDate startDate, LocalDate endDate, PaymentStatus paymentStatus, String enterpriseId);
}