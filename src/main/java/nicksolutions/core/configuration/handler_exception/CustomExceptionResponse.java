package nicksolutions.core.configuration.handler_exception;

import java.time.LocalDateTime;

public class CustomExceptionResponse {

  private final String message;
  private final LocalDateTime timestamp;
  private final int status;

  public CustomExceptionResponse(String message, int status) {
    this.message = message;
    this.status = status;
    this.timestamp = LocalDateTime.now();
  }

  public String getMessage() { return message; }
  public LocalDateTime getTimestamp() { return timestamp; }
  public int getStatus() { return status; }
}
