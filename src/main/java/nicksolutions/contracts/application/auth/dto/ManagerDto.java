package nicksolutions.contracts.application.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ManagerDto implements Serializable {

  @Serial
  private static final long serialVersionUID = -2063444471382853707L;

  private String id;
  private String name;
  private String email;
  private String password;
  private String phoneNumber;
  private Long version;
}
