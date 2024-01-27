package duke.exceptions;

public class InvalidParametersException extends InvalidInputException {
  public InvalidParametersException() {
    super("That's an invalid parameter, " +
      "make sure to use /by to indicate time if you are creating a deadline, " +
      "or /from and /to if you are creating a event!");
  }
}
