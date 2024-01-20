package exceptions;

public class BadTaskInputException extends  BadInputException{
  public BadTaskInputException(String message, String expected, String example, String got) {
    super(message, expected, example, got);
  }
}
