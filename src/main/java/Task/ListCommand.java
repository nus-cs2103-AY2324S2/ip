package Task;

public class ListCommand extends Command {
  public ListCommand() {
    super("");
  }

  String execute(TaskList list) {
    return list.toString();
  }
}
