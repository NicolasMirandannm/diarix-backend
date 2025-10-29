package nicksolutions.contracts.application.dailywage;

import nicksolutions.contracts.application.dailywage.dto.DailyWageDto;
import nicksolutions.contracts.application.dayLaborer.dto.DayLaborerDto;
import nicksolutions.contracts.domain.dailywage.DailyWage;
import nicksolutions.contracts.domain.dailywage.service.DailyWageService;
import nicksolutions.core.crud.AbstractApplicationComponent;
import nicksolutions.core.crud.ApplicationMapper;
import nicksolutions.core.shared.PaymentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DailyWageComponentImpl extends AbstractApplicationComponent<DailyWageDto, DailyWage, DailyWageService> implements DailyWageComponent {

  protected DailyWageComponentImpl(ApplicationMapper<DailyWageDto, DailyWage> mapper, DailyWageService service) {
    super(mapper, service);
  }

  @Override
  public Page<DailyWageDto> findWithFilters(String dayLaborerName, String enterpriseName,
                                            LocalDate workDate, PaymentStatus status, Pageable pageable) {
    return service.findWithFilters(dayLaborerName, enterpriseName, workDate, status, pageable)
        .map(mapper::toDto);
  }

  @Override
  public DailyWageDto create(DailyWageDto dailyWageDto) {
    if (dailyWageDto.getDayLaborer() != null && dailyWageDto.getDayLaborer().size() > 1) {
      return saveMultipleDailyWages(dailyWageDto);
    } else {
      return super.create(dailyWageDto);
    }
  }

  private DailyWageDto saveMultipleDailyWages(DailyWageDto dailyWageDto) {
    List<DailyWage> savedEntities = new ArrayList<>();

    for (DayLaborerDto dayLaborerDto : dailyWageDto.getDayLaborer()) {
      DailyWageDto individualDto = DailyWageDto.builder()
          .enterprise(dailyWageDto.getEnterprise())
          .dayLaborer(List.of(dayLaborerDto))
          .baseDailyRate(dailyWageDto.getBaseDailyRate())
          .bonus(dayLaborerDto.getBonus())
          .deduction(dayLaborerDto.getDeduction())
          .paymentValue(dayLaborerDto.getPaymentValue())
          .notes(dailyWageDto.getNotes())
          .paymentStatus(dailyWageDto.getPaymentStatus())
          .workDate(dailyWageDto.getWorkDate())
          .startHour(dailyWageDto.getStartHour())
          .endHour(dailyWageDto.getEndHour())
          .build();

      DailyWage entity = mapper.toEntity(individualDto);
      DailyWage savedEntity = service.save(entity);
      savedEntities.add(savedEntity);
    }

    return mapper.toDto(savedEntities.get(0));
  }
}