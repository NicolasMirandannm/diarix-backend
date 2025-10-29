package nicksolutions.contracts.application.dailywage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DayLaborerWorkDto implements Serializable {

  @Serial
  private static final long serialVersionUID = 6782397240560271753L;

  private String dayLaborerId;
  private BigDecimal bonus;
  private BigDecimal deduction;
  private BigDecimal dayLaborerPaymentValue;
}
