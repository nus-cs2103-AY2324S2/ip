package Task;

public class UnmarkCommand extends Command {
  public UnmarkCommand(String body) {
    super(body);
  }

  public boolean execute(TaskList list) {
    String body = getBody();
    int index = Integer.parseInt(body);
    list.unmarkTaskAsDone(index);
    System.out.println("Undone: " + list.get(index - 1));
    return true;
  }
}
