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
    return DailyWageDto.builder()
        .id(entity.getId())
        .enterprise(toEnterpriseDto(entity.getEnterprise()))
        .dayLaborer(toDayLaborerDto(entity.getDayLaborer()))
        .baseDailyRate(entity.getBaseDailyRate())
        .bonus(entity.getBonus())
        .deduction(entity.getDeduction())
        .dayLaborerPaymentValue(entity.getDayLaborerPaymentValue())
        .notes(entity.getNotes())
        .paymentStatus(entity.getPaymentStatus())
        .workDate(entity.getWorkDate())
        .startHour(entity.getStartHour())
        .endHour(entity.getEndHour())
        .version(entity.getVersion())
        .build();
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
        .phoneNumber(dayLaborer.getPhoneNumber())
        .status(dayLaborer.getStatus())
        .pixKey(dayLaborer.getPixKey())
        .version(dayLaborer.getVersion())
        .build();
  }

  @Override
  public DailyWage toEntity(DailyWageDto dto) {
    var dailyWage = DailyWage.builder()
        .id(dto.getId())
        .enterprise(toEnterpriseEntity(dto.getEnterprise()))
        .dayLaborer(toDayLaborerEntity(dto.getDayLaborer()))
        .baseDailyRate(dto.getBaseDailyRate())
        .bonus(dto.getBonus())
        .deduction(dto.getDeduction())
        .dayLaborerPaymentValue(dto.getDayLaborerPaymentValue())
        .notes(dto.getNotes())
        .paymentStatus(dto.getPaymentStatus())
        .workDate(dto.getWorkDate())
        .startHour(dto.getStartHour())
        .endHour(dto.getEndHour())
        .build();
    dailyWage.setVersion(dto.getVersion());
    return dailyWage;
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
