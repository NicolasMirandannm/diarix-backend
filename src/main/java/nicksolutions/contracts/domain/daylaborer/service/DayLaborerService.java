package nicksolutions.contracts.domain.daylaborer.service;

import nicksolutions.contracts.domain.daylaborer.DayLaborer;
import nicksolutions.core.crud.BaseService;
import nicksolutions.core.shared.StatusRegister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface DayLaborerService extends BaseService<DayLaborer> {

  Page<DayLaborer> availableLaborers(LocalDate date, LocalTime startHour, LocalTime endHour, Pageable pageable);

  Page<DayLaborer> findWithFilters(String name, String cpf, String phoneNumber, StatusRegister status, Pageable pageable);

  List<DayLaborer> findAllWithPendingPayments();
}
