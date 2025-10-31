package nicksolutions.contracts.domain.dailywage.usecase;

import nicksolutions.contracts.application.dailywage.dto.DailyWorkRegisterDto;
import nicksolutions.contracts.application.dailywage.dto.DayLaborerWorkDto;
import nicksolutions.contracts.domain.dailywage.DailyWage;
import nicksolutions.contracts.domain.dailywage.service.DailyWageService;
import nicksolutions.contracts.domain.daylaborer.DayLaborer;
import nicksolutions.contracts.domain.enterprise.Enterprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class DailyWorkRegister {

  private final DailyWageService dailyWageService;

  @Autowired
  public DailyWorkRegister(DailyWageService dailyWageService) {
    this.dailyWageService = dailyWageService;
  }

  @Transactional
  public List<DailyWage> execute(DailyWorkRegisterDto dailyWorkRegisterDto) {
    return dailyWorkRegisterDto.getDayLaborers().stream()
        .map(dayLaborerWorkDto -> getDailyWage(dailyWorkRegisterDto, dayLaborerWorkDto))
        .map(dailyWageService::save)
        .toList();
  }

  private DailyWage getDailyWage(DailyWorkRegisterDto dailyWorkRegisterDto, DayLaborerWorkDto dayLaborerWorkDto) {
    return DailyWage.builder()
        .dayLaborer(DayLaborer.builder()
            .id(dayLaborerWorkDto.getDayLaborerId())
            .build())
        .enterprise(Enterprise.builder()
            .id(dailyWorkRegisterDto.getEnterpriseId())
            .build())
        .workDate(dailyWorkRegisterDto.getWorkDate())
        .startHour(dailyWorkRegisterDto.getStartHour())
        .endHour(dailyWorkRegisterDto.getEndHour())
        .notes(dailyWorkRegisterDto.getNotes())
        .baseDailyRate(dailyWorkRegisterDto.getBaseDailyRate())
        .bonus(dayLaborerWorkDto.getBonus())
        .deduction(dayLaborerWorkDto.getDeduction())
        .dayLaborerPaymentValue(dayLaborerWorkDto.getDayLaborerPaymentValue())
        .build();
  }
}
