package Task;

public class EmptyTaskDescriptionException extends DukeException {
  public EmptyTaskDescriptionException(String message, String botMessage) {
    super(message, botMessage);
  }
}
