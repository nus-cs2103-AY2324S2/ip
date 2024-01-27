package duke.exceptions;

public class InvalidTaskException extends InvalidInputException {
  public InvalidTaskException() {
    super("That's an invalid task type! " +
      "Try choosing one from the following: todo | event | deadline");
  }
}
