package nicksolutions.contracts.application.dailywage;

import nicksolutions.contracts.application.dailywage.dto.DailyWageDto;
import nicksolutions.contracts.application.dayLaborer.dto.DayLaborerDto;
import nicksolutions.contracts.application.enterprise.dto.EnterpriseDto;
import nicksolutions.contracts.domain.dailywage.DailyWage;
import nicksolutions.contracts.domain.daylaborer.DayLaborer;
import nicksolutions.contracts.domain.enterprise.Enterprise;
import nicksolutions.core.crud.ApplicationMapper;
import org.springframework.stereotype.Component;

@Component
public class DailyWageMapper implements ApplicationMapper<DailyWageDto, DailyWage> {

  @Override
  public DailyWageDto toDto(DailyWage entity) {
    var dto = DailyWageDto.builder()
        .id(entity.getId())
        .enterprise(toEnterpriseDto(entity.getEnterprise()))
        .dayLaborer(toDayLaborerDto(entity.getDayLaborer()))
        .baseDailyRate(entity.getBaseDailyRate())
        .bonus(entity.getBonus())
        .deduction(entity.getDeduction())
        .totalPayment(entity.getTotalPayment())
        .notes(entity.getNotes())
        .paymentStatus(entity.getPaymentStatus())
        .build();
    dto.setVersion(entity.getVersion());
    return dto;
  }

  private EnterpriseDto toEnterpriseDto(Enterprise enterprise) {
    return EnterpriseDto.builder()
        .id(enterprise.getId())
        .name(enterprise.getName())
        .cnpj(enterprise.getCnpj())
        .build();
  }

  private DayLaborerDto toDayLaborerDto(DayLaborer dayLaborer) {
    return DayLaborerDto.builder()
        .id(dayLaborer.getId())
        .name(dayLaborer.getName())
        .cpf(dayLaborer.getCpf())
        .build();
  }

  @Override
  public DailyWage toEntity(DailyWageDto dailyWageDto) {
    var entity = DailyWage.builder()
        .id(dailyWageDto.getId())
        .enterprise(toEnterpriseEntity(dailyWageDto.getEnterprise()))
        .dayLaborer(toDayLaborerEntity(dailyWageDto.getDayLaborer()))
        .baseDailyRate(dailyWageDto.getBaseDailyRate())
        .bonus(dailyWageDto.getBonus())
        .deduction(dailyWageDto.getDeduction())
        .totalPayment(dailyWageDto.getTotalPayment())
        .notes(dailyWageDto.getNotes())
        .paymentStatus(dailyWageDto.getPaymentStatus())
        .build();
    entity.setVersion(dailyWageDto.getVersion());
    return entity;
  }

  private Enterprise toEnterpriseEntity(EnterpriseDto enterpriseDto) {
    return Enterprise.builder()
        .id(enterpriseDto.getId())
        .build();
  }

  private DayLaborer toDayLaborerEntity(DayLaborerDto dayLaborerDto) {
    return DayLaborer.builder()
        .id(dayLaborerDto.getId())
        .build();
  }
}
