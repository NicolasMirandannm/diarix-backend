package nicksolutions.contracts.domain.auth;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import nicksolutions.core.crud.BaseEntity;

@Data
@Entity
@Table(name = "contract_manager")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ContractManager extends BaseEntity {

  @Id
  private String id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "password", nullable = false)
  private String password;
}

