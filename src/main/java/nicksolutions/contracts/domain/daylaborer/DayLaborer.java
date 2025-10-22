package nicksolutions.contracts.domain.daylaborer;

import jakarta.persistence.*;
import lombok.*;
import nicksolutions.core.crud.BaseEntityMultiTenancy;
import nicksolutions.core.shared.StatusRegister;
import org.hibernate.annotations.TenantId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "day_laborer")
@EqualsAndHashCode(callSuper = false)
public class DayLaborer extends BaseEntityMultiTenancy {

    @Id
    private String id;

    @TenantId
    @Column(name = "manager_id")
    private String ManagerId;

    @Column(name = "name")
    private String name;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusRegister status;

    @Column(name = "pix_key")
    private String pixKey;
}
