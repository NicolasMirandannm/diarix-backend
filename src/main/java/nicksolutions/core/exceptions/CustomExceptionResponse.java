package nicksolutions.core.exceptions;

import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;

@Getter
public class CustomExceptionResponse implements Serializable {

  @Serial
  private static final long serialVersionUID = 7113690606163814328L;

  private final String message;
  private final OffsetDateTime timestamp;
  private final int status;

  public CustomExceptionResponse(String message, int status) {
    this.message = message;
    this.status = status;
    this.timestamp = OffsetDateTime.now();
  }

}
