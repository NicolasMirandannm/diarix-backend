package nicksolutions.contracts.domain.dailywage;

import nicksolutions.core.crud.BaseRepository;
import nicksolutions.core.shared.PaymentStatus;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface DailyWageRepository extends BaseRepository<DailyWage>, JpaSpecificationExecutor<DailyWage> {

  List<DailyWage> findAllByIdIn(List<String> ids);

  List<DailyWage> findByDayLaborerIdAndWorkDateBetweenAndPaymentStatus(String dayLaborerId, LocalDate workDateAfter, LocalDate workDateBefore, PaymentStatus paymentStatus);

  List<DailyWage> findByDayLaborerIdAndEnterpriseIdAndWorkDateBetweenAndPaymentStatus(String dayLaborerId, String enterpriseId, LocalDate startDate, LocalDate endDate, PaymentStatus paymentStatus);

  long countByWorkDateBetween(LocalDate startDate, LocalDate endDate);

  @Query("""
    SELECT COALESCE(SUM(dw.baseDailyRate - (dw.dayLaborerPaymentValue + dw.bonus - dw.deduction)), 0)
    FROM DailyWage dw
    WHERE dw.paymentStatus = 'PAGO'
      AND MONTH(dw.workDate) = MONTH(CURRENT_DATE)
      AND YEAR(dw.workDate) = YEAR(CURRENT_DATE)
  """)
  Double findTotalProfitForCurrentMonth();
}
