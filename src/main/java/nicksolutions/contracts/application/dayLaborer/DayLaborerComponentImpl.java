package nicksolutions.contracts.application.dayLaborer;

import nicksolutions.contracts.application.dayLaborer.dto.DayLaborerDto;
import nicksolutions.contracts.domain.daylaborer.DayLaborer;
import nicksolutions.contracts.domain.daylaborer.service.DayLaborerService;
import nicksolutions.core.crud.AbstractApplicationComponent;
import nicksolutions.core.crud.ApplicationMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class DayLaborerComponentImpl extends AbstractApplicationComponent<DayLaborerDto, DayLaborer, DayLaborerService> implements DayLaborerComponent {

    protected DayLaborerComponentImpl(ApplicationMapper<DayLaborerDto, DayLaborer> mapper, DayLaborerService service) {
        super(mapper, service);
    }

  @Override
  public Page<DayLaborerDto> findAvailable(LocalDate date, LocalTime startHour, LocalTime endHour, Pageable pageable) {
    return service.availableLaborers(date, startHour, endHour, pageable)
        .map(mapper::toDto);
  }
}
