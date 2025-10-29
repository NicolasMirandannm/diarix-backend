package nicksolutions.contracts.application.dailywage;

import nicksolutions.contracts.application.dailywage.dto.DailyWageDto;
import nicksolutions.core.crud.ApplicationComponent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import nicksolutions.core.shared.PaymentStatus;

import java.time.LocalDate;

public interface DailyWageComponent extends ApplicationComponent<DailyWageDto> {

  Page<DailyWageDto> findWithFilters(String dayLaborerName, String enterpriseName,
                                     LocalDate workDate, PaymentStatus status, Pageable pageable);

}