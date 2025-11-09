package nicksolutions.contracts.application.payment;

import nicksolutions.contracts.application.dailywage.dto.DailyWageDto;
import nicksolutions.contracts.application.dayLaborer.dto.DayLaborerDto;
import nicksolutions.contracts.application.enterprise.dto.EnterpriseDto;
import nicksolutions.contracts.application.payment.dto.PaymentDto;
import nicksolutions.contracts.domain.dailywage.DailyWage;
import nicksolutions.contracts.domain.payment.Payment;
import nicksolutions.core.crud.ApplicationMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentMapper implements ApplicationMapper<PaymentDto, Payment> {

  @Override
  public PaymentDto toDto(Payment entity) {
    return PaymentDto.builder()
        .id(entity.getId())
        .date(entity.getDate())
        .value(entity.getValue())
        .method(entity.getMethod())
        .observations(entity.getObservations())
        .dailyWages(entity.getDailyWages().stream().map(this::toDailyWageDto).toList())
        .dayLaborer(toDayLaborerDto(entity.getDailyWages()))
        .version(entity.getVersion())
        .build();
  }

  private DayLaborerDto toDayLaborerDto(List<DailyWage> dailyWages) {
    return dailyWages.stream()
        .findFirst()
        .map(dailyWage -> DayLaborerDto.builder()
            .id(dailyWage.getDayLaborer().getId())
            .name(dailyWage.getDayLaborer().getName())
            .pixKey(dailyWage.getDayLaborer().getPixKey())
            .build())
        .orElse(null);
  }

  private DailyWageDto toDailyWageDto(DailyWage dailyWage) {
    return DailyWageDto.builder()
        .id(dailyWage.getId())
        .workDate(dailyWage.getWorkDate())
        .valuePaid(dailyWage.computePaymentValue())
        .enterprise(EnterpriseDto.builder()
            .name(dailyWage.getEnterprise().getName())
            .build())
        .dayLaborer(DayLaborerDto.builder()
            .name(dailyWage.getDayLaborer().getName())
            .build())
        .build();
  }

  @Override
  public Payment toEntity(PaymentDto paymentDto) {
    Payment payment = Payment.builder()
        .id(paymentDto.getId())
        .date(paymentDto.getDate())
        .value(paymentDto.getValue())
        .method(paymentDto.getMethod())
        .observations(paymentDto.getObservations())
        .dailyWages(paymentDto.getDailyWages().stream().map(this::toDailyWageEntity).toList())
        .build();
    payment.setVersion(paymentDto.getVersion());
    return payment;
  }

  private DailyWage toDailyWageEntity(DailyWageDto dailyWageDto) {
    return DailyWage.builder()
        .id(dailyWageDto.getId())
        .build();
  }
}
