package nicksolutions.contracts.domain.enterprise;

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
@Table(name = "enterprise")
@EqualsAndHashCode(callSuper = false)
public class Enterprise extends BaseEntityMultiTenancy {

  @Id
  private String id;

  @TenantId
  @Column(name = "contract_manager_id")
  private String contractManagerId;

  @Column(name = "name")
  private String name;

  @Column(name = "cnpj")
  private String cnpj;

  @Column(name = "owner_name")
  private String ownerName;

  @Column(name = "owner_email")
  private String ownerEmail;

  @Column(name = "owner_phone")
  private String ownerPhone;

  @Column(name = "address")
  private String address;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private StatusRegister status;
}


