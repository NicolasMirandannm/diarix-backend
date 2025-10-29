package nicksolutions.contracts.domain.daylaborer;

import nicksolutions.core.crud.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;

public interface DayLaborerRepository extends BaseRepository<DayLaborer> {


  @Query("""
  SELECT dl FROM DayLaborer dl
  WHERE dl.status = 'ATIVO'
    AND NOT EXISTS (
        SELECT 1 FROM DailyWage dw
        WHERE dw.dayLaborer = dl
          AND dw.workDate = :date
          AND (dw.startHour < :endHour AND dw.endHour > :startHour)
    )
""")
  Page<DayLaborer> findAvailableLaborers(
      @Param("date") LocalDate date,
      @Param("startHour") LocalTime startHour,
      @Param("endHour") LocalTime endHour,
      Pageable pageable
  );
}
