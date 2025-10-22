package nicksolutions.contracts.application.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenDto implements Serializable {

  @Serial
  private static final long serialVersionUID = -3822625535342462921L;

  @Builder.Default
  private String type = "Bearer";
  private String token;
  private String ManagerId;
}
