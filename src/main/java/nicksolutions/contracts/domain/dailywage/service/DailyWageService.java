package nicksolutions.contracts.domain.dailywage.service;

import nicksolutions.contracts.domain.dailywage.DailyWage;
import nicksolutions.core.crud.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import nicksolutions.core.shared.PaymentStatus;

import java.time.LocalDate;

public interface DailyWageService extends BaseService<DailyWage> {

  Page<DailyWage> findWithFilters(String dayLaborerName, String enterpriseName,
                                  LocalDate workDate, PaymentStatus status, Pageable pageable);

}