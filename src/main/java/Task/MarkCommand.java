package Task;

public class MarkCommand extends Command {
  public MarkCommand(String body) {
    super(body);
  }

  public boolean execute(TaskList list) {
    String body = getBody();
    int index = Integer.parseInt(body);
    list.markTaskAsDone(index);
    System.out.println("Done: " + list.get(index - 1));
    return true;
  }
}
