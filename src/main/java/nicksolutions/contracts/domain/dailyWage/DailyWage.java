package nicksolutions.contracts.domain.dailyWage;

import jakarta.persistence.*;
import lombok.*;
import nicksolutions.core.crud.BaseEntityMultiTenancy;
import nicksolutions.core.shared.PaymentStatus;
import org.hibernate.annotations.TenantId;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "daily_wage")
@EqualsAndHashCode(callSuper = false)
public class DailyWage extends BaseEntityMultiTenancy {

    @Id
    private String id;

    @TenantId
    @Column(name = "contract_manager_id")
    private String contractManagerId;

    @Column(name = "enterprise_id")
    private String enterpriseId;

    @Column(name = "day_laborer_id")
    private String dayLaborerId;

    @Column(name = "base_daily_rate")
    private BigDecimal baseDailyRate;

    @Column(name = "bonus")
    private BigDecimal bonus;

    @Column(name = "deduction")
    private BigDecimal deduction;

    @Column(name = "total_payment")
    private BigDecimal totalPayment;

    @Column(name = "notes")
    private String notes;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;
}
