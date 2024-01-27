package duke.exceptions;

public class InvalidDateTimeException extends InvalidInputException {
  public InvalidDateTimeException () {
    super("That's not a valid date time, remember to give it in a " +
      "`YYYY-MM-DD HHHH` format!");
  }
}
