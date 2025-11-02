package nicksolutions.contracts.domain.dailywage;

import jakarta.persistence.*;
import lombok.*;
import nicksolutions.contracts.domain.daylaborer.DayLaborer;
import nicksolutions.contracts.domain.enterprise.Enterprise;
import nicksolutions.contracts.domain.payment.Payment;
import nicksolutions.core.crud.BaseEntityMultiTenancy;
import nicksolutions.core.shared.PaymentStatus;
import org.hibernate.annotations.TenantId;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enterprise_id", insertable = false, updatable = false, nullable = false)
    private Enterprise enterprise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "day_laborer_id", insertable = false, updatable = false, nullable = false)
    private DayLaborer dayLaborer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id", insertable = false, updatable = false)
    private Payment payment;

    @Column(name = "base_daily_rate")
    private BigDecimal baseDailyRate;

    @Column(name = "bonus")
    private BigDecimal bonus;

    @Column(name = "deduction")
    private BigDecimal deduction;

    @Column(name = "day_laborer_payment_value")
    private BigDecimal dayLaborerPaymentValue;

    @Column(name = "notes")
    private String notes;

    @Column(name = "work_date")
    private LocalDate workDate;

    @Column(name = "start_hour")
    private LocalTime startHour;

    @Column(name = "end_hour")
    private LocalTime endHour;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    public BigDecimal computePaymentValue() {
        BigDecimal dayLaborerValue = Optional.ofNullable(dayLaborerPaymentValue).orElse(BigDecimal.ZERO);
        BigDecimal bonusValue = Optional.ofNullable(bonus).orElse(BigDecimal.ZERO);
        BigDecimal deductionValue = Optional.ofNullable(deduction).orElse(BigDecimal.ZERO);

        return dayLaborerValue.add(bonusValue).subtract(deductionValue);
    }
}
