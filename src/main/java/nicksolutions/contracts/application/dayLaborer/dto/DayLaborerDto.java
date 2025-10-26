package nicksolutions.contracts.application.dayLaborer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nicksolutions.core.shared.StatusRegister;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DayLaborerDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String cpf;
    private String phoneNumber;
    private StatusRegister status;
    private String pixKey;
    private Long version;
    private BigDecimal deduction;
    private BigDecimal bonus;
    private BigDecimal paymentValue;
}
