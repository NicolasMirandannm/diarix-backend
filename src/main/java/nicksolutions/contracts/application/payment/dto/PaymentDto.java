package nicksolutions.contracts.application.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nicksolutions.contracts.application.dailywage.dto.DailyWageDto;
import nicksolutions.contracts.application.dayLaborer.dto.DayLaborerDto;
import nicksolutions.contracts.domain.payment.PaymentMethod;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto implements Serializable {

  @Serial
  private static final long serialVersionUID = 819261159024098657L;

  private String id;
  private List<DailyWageDto> dailyWages;
  private DayLaborerDto dayLaborer;
  private LocalDate date;
  private BigDecimal value;
  private PaymentMethod method;
  private String observations;
  private Long version;
}
