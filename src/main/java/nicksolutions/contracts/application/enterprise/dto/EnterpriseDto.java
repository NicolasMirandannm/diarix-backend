package nicksolutions.contracts.application.enterprise.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nicksolutions.core.shared.StatusRegister;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnterpriseDto implements Serializable {

  @Serial
  private static final long serialVersionUID = 1263018507505133078L;

  private String id;
  private String name;
  private String cnpj;
  private String ownerName;
  private String ownerEmail;
  private String ownerPhone;
  private String address;
  private StatusRegister status;
  private Long version;

}
