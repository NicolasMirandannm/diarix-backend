package nicksolutions.contracts.application.dailywage;

import nicksolutions.contracts.application.dailywage.dto.DailyWageDto;
import nicksolutions.contracts.application.dayLaborer.dto.DayLaborerDto;
import nicksolutions.contracts.domain.dailywage.DailyWage;
import nicksolutions.contracts.domain.dailywage.service.DailyWageService;
import nicksolutions.core.crud.AbstractApplicationComponent;
import nicksolutions.core.crud.ApplicationMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DailyWageComponentImpl extends AbstractApplicationComponent<DailyWageDto, DailyWage, DailyWageService> implements DailyWageComponent {

  protected DailyWageComponentImpl(ApplicationMapper<DailyWageDto, DailyWage> mapper, DailyWageService service) {
    super(mapper, service);
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