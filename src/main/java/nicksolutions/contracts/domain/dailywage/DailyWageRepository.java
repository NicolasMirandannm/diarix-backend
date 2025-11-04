package nicksolutions.contracts.domain.dailywage;

import nicksolutions.core.crud.BaseRepository;
import nicksolutions.core.shared.PaymentStatus;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.List;

public interface DailyWageRepository extends BaseRepository<DailyWage>, JpaSpecificationExecutor<DailyWage> {

  List<DailyWage> findAllByIdIn(List<String> ids);

  List<DailyWage> findByDayLaborerIdAndWorkDateBetweenAndPaymentStatus(String dayLaborerId, LocalDate workDateAfter, LocalDate workDateBefore, PaymentStatus paymentStatus);

  List<DailyWage> findByDayLaborerIdAndEnterpriseIdAndWorkDateBetweenAndPaymentStatus(String dayLaborerId, String enterpriseId, LocalDate startDate, LocalDate endDate, PaymentStatus paymentStatus);
}
