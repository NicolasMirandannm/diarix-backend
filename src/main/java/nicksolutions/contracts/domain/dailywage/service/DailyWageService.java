package nicksolutions.contracts.domain.dailywage.service;

import nicksolutions.contracts.domain.dailywage.DailyWage;
import nicksolutions.core.crud.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import nicksolutions.core.shared.PaymentStatus;

import java.time.LocalDate;
import java.util.List;

public interface DailyWageService extends BaseService<DailyWage> {

  Page<DailyWage> findWithFilters(String dayLaborerName, String enterpriseName,
                                  LocalDate workDate, PaymentStatus status, Pageable pageable);

  List<DailyWage> findByIds(List<String> ids);

  List<DailyWage> updatePaymentStatus(List<String> dailyWageIds, PaymentStatus paymentStatus);
}