package nicksolutions.contracts.domain.auth;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import nicksolutions.core.crud.BaseEntityNonMultiTenancy;

@Data
@Entity
@Table(name = "manager")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Manager extends BaseEntityNonMultiTenancy {

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

