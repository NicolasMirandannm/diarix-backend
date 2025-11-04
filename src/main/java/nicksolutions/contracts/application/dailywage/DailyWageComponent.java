package nicksolutions.contracts.application.dailywage;

import nicksolutions.contracts.application.dailywage.dto.DailyWageDto;
import nicksolutions.contracts.application.dailywage.dto.DailyWorkRegisterDto;
import nicksolutions.core.crud.ApplicationComponent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import nicksolutions.core.shared.PaymentStatus;

import java.time.LocalDate;

import java.util.List;

public interface DailyWageComponent extends ApplicationComponent<DailyWageDto> {

  List<DailyWageDto> dailyWorkRegister(DailyWorkRegisterDto dailyWorkRegisterDto);


  Page<DailyWageDto> findWithFilters(String dayLaborerName, String enterpriseName,
                                     LocalDate workDate, PaymentStatus status, Pageable pageable);

  List<DailyWageDto> findByDayLaborerIdAndFilters(String dayLaborerId, LocalDate startDate, LocalDate endDate, PaymentStatus paymentStatus, String enterpriseId);

  long countFromCurrentMonth();

  Double findTotalProfitForCurrentMonth();
}