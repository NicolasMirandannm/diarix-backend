package nicksolutions.contracts.domain.dailywage;

import jakarta.persistence.*;
import lombok.*;
import nicksolutions.contracts.domain.daylaborer.DayLaborer;
import nicksolutions.contracts.domain.enterprise.Enterprise;
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
    @Column(name = "manager_id")
    private String managerId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "enterprise_id", nullable = false)
    private Enterprise enterprise;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "day_laborer_id", nullable = false)
    private DayLaborer dayLaborer;

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
