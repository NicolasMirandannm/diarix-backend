package nicksolutions.contracts.domain.daylaborer.service;

import nicksolutions.contracts.domain.daylaborer.DayLaborer;
import nicksolutions.contracts.domain.daylaborer.DayLaborerRepository;
import nicksolutions.core.crud.BaseAbstractServiceImpl;
import nicksolutions.core.shared.StatusRegister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class DayLaborerServiceImpl extends BaseAbstractServiceImpl<DayLaborer, DayLaborerRepository> implements DayLaborerService {

    public DayLaborerServiceImpl(DayLaborerRepository repository) {
        super(repository);
    }

    @Override
    public DayLaborer save(DayLaborer entity) {
        if (entity.isNew()) {
            entity.setStatus(StatusRegister.ATIVO);
        }
        return super.save(entity);
    }

  @Override
  public Page<DayLaborer> availableLaborers(LocalDate date, LocalTime startHour, LocalTime endHour, Pageable pageable) {
    return repository.findAvailableLaborers(date, startHour, endHour, pageable);
  }
}
