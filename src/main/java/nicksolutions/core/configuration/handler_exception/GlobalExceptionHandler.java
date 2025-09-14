package nicksolutions.core.configuration.handler_exception;

import nicksolutions.core.exceptions.CustomHttpException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(CustomHttpException.class)
  public ResponseEntity<CustomExceptionResponse> handleCustomHttpException(CustomHttpException ex) {
    CustomExceptionResponse error = new CustomExceptionResponse(ex.getMessage(), ex.getStatus().value());
    return ResponseEntity.status(ex.getStatus()).body(error);
  }
}
