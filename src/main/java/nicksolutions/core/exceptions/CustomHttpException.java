package nicksolutions.core.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomHttpException extends RuntimeException {
  private final HttpStatus status;

  public CustomHttpException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }

  public static CustomHttpException notFound(String message) {
    return new CustomHttpException(message, HttpStatus.NOT_FOUND);
  }
}
