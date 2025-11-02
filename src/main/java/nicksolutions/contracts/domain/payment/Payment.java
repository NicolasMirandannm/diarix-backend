package nicksolutions.contracts.domain.payment;

import jakarta.persistence.*;
import lombok.*;
import nicksolutions.contracts.domain.dailywage.DailyWage;
import nicksolutions.core.crud.BaseEntityMultiTenancy;
import nicksolutions.core.shared.StatusRegister;
import org.hibernate.annotations.TenantId;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "payment")
@EqualsAndHashCode(callSuper = false)
public class Payment extends BaseEntityMultiTenancy {

  @Id
  private String id;

  @TenantId
  @Column(name = "manager_id")
  private String managerId;

  @OneToMany(fetch = FetchType.EAGER)
  @JoinColumn(name = "payment_id", updatable = false, insertable = false, nullable = false)
  private List<DailyWage> dailyWages;

  @Column(name = "date")
  private LocalDateTime date;

  @Enumerated(EnumType.STRING)
  @Column(name = "method")
  private PaymentMethod method;

  @Column(name = "value")
  private BigDecimal value;

  @Column(name = "observations")
  private String observations;

  public List<String> getDailyWageIds() {
    return dailyWages.stream().map(DailyWage::getId).collect(Collectors.toList());
  }
}


