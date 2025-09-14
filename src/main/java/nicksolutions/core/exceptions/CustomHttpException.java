package nicksolutions.core.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
public class CustomHttpException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = -3436018996639534695L;

  private final HttpStatus status;

  public CustomHttpException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }

  public static CustomHttpException notFound(String message) {
    return new CustomHttpException(message, HttpStatus.NOT_FOUND);
  }

  public static CustomHttpException badRequest(String message) {
    return new CustomHttpException(message, HttpStatus.BAD_REQUEST);
  }

  public static CustomHttpException forbidden(String message) {
    return new CustomHttpException(message, HttpStatus.FORBIDDEN);
  }
}
