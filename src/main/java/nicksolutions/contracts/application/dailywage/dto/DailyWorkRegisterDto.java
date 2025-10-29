package nicksolutions.contracts.application.dailywage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DailyWorkRegisterDto implements Serializable {

  @Serial
  private static final long serialVersionUID = 8709061158790889271L;

  private String enterpriseId;
  private BigDecimal baseDailyRate;
  private String notes;
  private LocalDate workDate;
  private LocalTime startHour;
  private LocalTime endHour;
  @Builder.Default
  private List<DayLaborerWorkDto> dayLaborers = new ArrayList<>();
}
