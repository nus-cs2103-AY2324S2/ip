package Task;

public class UnmarkCommand extends Command {
  public UnmarkCommand(String body) {
    super(body);
  }

  String execute(TaskList list) {
    String body = getBody();
    int index = Integer.parseInt(body);
    list.unmarkTaskAsDone(index);
    return "Undone: " + list.get(index - 1);
  }
}
