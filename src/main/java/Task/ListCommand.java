package Task;

public class ListCommand extends Command {
  public ListCommand() {
    super("");
  }

  public boolean execute(TaskList list) {
    System.out.println(list.toString());
    return true;
  }
}
