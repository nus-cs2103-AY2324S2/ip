package Task;

public abstract class Command {
  private String body;

  protected Command(String body) {
    this.body = body;
  }

  protected String getBody() {
    return body;
  }

  public static Command newCommand(String name, String body) {
    switch (name) {
      case "list":
        return new ListCommand();
      case "mark":
        return new MarkCommand(body);
      case "unmark":
        return new UnmarkCommand(body);
      case "bye":
        return new ExitCommand();
      case "todo":
        return new TodoCommand(body);
      case "deadline":
        return new DeadlineCommand(body);
      case "event":
        return new EventCommand(body);
      default:
        return new AddCommand(body);
    }
  }

  abstract public boolean execute(TaskList list);
}
