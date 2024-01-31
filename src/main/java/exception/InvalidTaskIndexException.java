package exception;

public class InvalidTaskIndexException extends Exception {
  public InvalidTaskIndexException(String message) {
    super(message);
  }

  public String getErrorMessage() {
    return super.getMessage();
  }
}
