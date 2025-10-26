package nicksolutions.contracts.application.dailywage;

import nicksolutions.contracts.application.dailywage.dto.DailyWageDto;
import nicksolutions.contracts.application.dayLaborer.dto.DayLaborerDto;
import nicksolutions.contracts.application.enterprise.dto.EnterpriseDto;
import nicksolutions.contracts.domain.dailywage.DailyWage;
import nicksolutions.contracts.domain.daylaborer.DayLaborer;
import nicksolutions.contracts.domain.daylaborer.DayLaborerRepository;
import nicksolutions.contracts.domain.enterprise.Enterprise;
import nicksolutions.contracts.domain.enterprise.EnterpriseRepository;
import nicksolutions.core.crud.ApplicationMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DailyWageMapper implements ApplicationMapper<DailyWageDto, DailyWage> {

  private final EnterpriseRepository enterpriseRepository;
  private final DayLaborerRepository dayLaborerRepository;

  public DailyWageMapper(EnterpriseRepository enterpriseRepository,
                         DayLaborerRepository dayLaborerRepository) {
    this.enterpriseRepository = enterpriseRepository;
    this.dayLaborerRepository = dayLaborerRepository;
  }

  @Override
  public DailyWageDto toDto(DailyWage entity) {
    var dto = DailyWageDto.builder()
        .id(entity.getId())
        .enterprise(toEnterpriseDto(entity.getEnterprise()))
        .dayLaborer(List.of(toDayLaborerDto(entity.getDayLaborer())))
        .baseDailyRate(entity.getBaseDailyRate())
        .bonus(entity.getBonus())
        .deduction(entity.getDeduction())
        .paymentValue(entity.getPaymentValue())
        .notes(entity.getNotes())
        .paymentStatus(entity.getPaymentStatus())
        .workDate(entity.getWorkDate())
        .startHour(entity.getStartHour())
        .endHour(entity.getEndHour())
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
        .phoneNumber(dayLaborer.getPhoneNumber())
        .status(dayLaborer.getStatus())
        .pixKey(dayLaborer.getPixKey())
        .version(dayLaborer.getVersion())
        .build();
  }

  @Override
  public DailyWage toEntity(DailyWageDto dto) {
    DayLaborerDto firstDayLaborer = dto.getDayLaborer() != null &&
        !dto.getDayLaborer().isEmpty()
        ? dto.getDayLaborer().get(0)
        : null;

    var entity = DailyWage.builder()
        .id(dto.getId())
        .enterprise(toEnterpriseEntity(dto.getEnterprise()))
        .dayLaborer(firstDayLaborer != null ? toDayLaborerEntity(firstDayLaborer) : null)
        .baseDailyRate(dto.getBaseDailyRate())
        .bonus(dto.getBonus())
        .deduction(dto.getDeduction())
        .paymentValue(dto.getPaymentValue())
        .notes(dto.getNotes())
        .paymentStatus(dto.getPaymentStatus())
        .workDate(dto.getWorkDate())
        .startHour(dto.getStartHour())
        .endHour(dto.getEndHour())
        .build();
    entity.setVersion(dto.getVersion());
    return entity;
  }

  private Enterprise toEnterpriseEntity(EnterpriseDto enterpriseDto) {
    if (enterpriseDto == null || enterpriseDto.getId() == null) {
      return null;
    }
    return enterpriseRepository.findById(enterpriseDto.getId())
        .orElseThrow(() -> new RuntimeException("Empresa não encontrada: " + enterpriseDto.getId()));
  }

  private DayLaborer toDayLaborerEntity(DayLaborerDto dayLaborerDto) {
    if (dayLaborerDto == null || dayLaborerDto.getId() == null) {
      return null;
    }
    return dayLaborerRepository.findById(dayLaborerDto.getId())
        .orElseThrow(() -> new RuntimeException("Diarista não encontrado: " + dayLaborerDto.getId()));
  }
}
