package nicksolutions.contracts.configuration;

import nicksolutions.core.exceptions.CustomExceptionResponse;
import nicksolutions.core.exceptions.CustomHttpException;
import org.springframework.http.HttpStatus;
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

  @ExceptionHandler(Exception.class)
  public ResponseEntity<CustomExceptionResponse> handleCustomHttpException(Exception ex) {
    CustomExceptionResponse error = new CustomExceptionResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    return ResponseEntity.status(error.getStatus()).body(error);
  }
}
