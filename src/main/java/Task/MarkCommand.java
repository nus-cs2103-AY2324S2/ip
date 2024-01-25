package Task;

public class MarkCommand extends Command {
  public MarkCommand(String body) {
    super(body);
  }

  String execute(TaskList list) {
    String body = getBody();
    int index = Integer.parseInt(body);
    list.markTaskAsDone(index);
    return "Done: " + list.get(index - 1);
  }
}
