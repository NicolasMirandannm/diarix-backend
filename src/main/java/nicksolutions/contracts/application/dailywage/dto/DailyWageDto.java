package nicksolutions.contracts.application.dailywage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nicksolutions.contracts.application.dayLaborer.dto.DayLaborerDto;
import nicksolutions.contracts.application.enterprise.dto.EnterpriseDto;
import nicksolutions.core.shared.PaymentStatus;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DailyWageDto implements Serializable {

  @Serial
  private static final long serialVersionUID = 7936649627253603863L;

  private String id;
  private EnterpriseDto enterprise;
  private List<DayLaborerDto> dayLaborer;
  private BigDecimal baseDailyRate;
  private BigDecimal bonus;
  private BigDecimal deduction;
  private BigDecimal paymentValue;
  private String notes;
  private PaymentStatus paymentStatus;
  private LocalDate workDate;
  private LocalTime startHour;
  private LocalTime endHour;
  private Long version;
}
