package nicksolutions.contracts.configuration;

public class DailyWageException extends RuntimeException {

  private DailyWageException(String message) {
    super(message);
  }

  public static DailyWageException dayLaborerNotAvailable() {
    return new DailyWageException("O diarista já possui uma diária registrada nesta data.");
  }
}
