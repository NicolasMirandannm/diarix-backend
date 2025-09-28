package nicksolutions.contracts.domain.enterprise;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import nicksolutions.core.crud.BaseEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "enterprise")
@EqualsAndHashCode(callSuper = false)
public class Enterprise extends BaseEntity {

  @Id
  private String id;

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
}


