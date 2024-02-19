package duke;

/**
 * Represents an exception specific to the Duke application
 */
public class DukeException extends Exception {
  /**
   * Constructs a DukeException with the specified error message.
   *
   * @param message The error message associated with the exception.
   */
  public DukeException(String message) {
    super(message);
  }
}