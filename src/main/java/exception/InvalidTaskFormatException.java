package exception;

public class InvalidTaskFormatException extends Exception {
  public InvalidTaskFormatException(String message) {
    super(message);
  }

  public String getErrorMessage() {
    return super.getMessage();
  }
}
