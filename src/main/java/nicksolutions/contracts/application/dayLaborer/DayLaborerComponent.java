package nicksolutions.contracts.application.dayLaborer;

import nicksolutions.contracts.application.dayLaborer.dto.DayLaborerDto;
import nicksolutions.core.crud.ApplicationComponent;
import nicksolutions.core.shared.StatusRegister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface DayLaborerComponent extends ApplicationComponent<DayLaborerDto> {

  Page<DayLaborerDto> findAvailable(LocalDate date, LocalTime startHour, LocalTime endHour, Pageable pageable);

  Page<DayLaborerDto> findWithFilters(String name, String cpf, String phoneNumber, StatusRegister status, Pageable pageable);

  List<DayLaborerDto> findAllWithPendingPayments();
}
