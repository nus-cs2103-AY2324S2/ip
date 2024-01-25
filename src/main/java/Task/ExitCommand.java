package Task;

public class ExitCommand extends Command {
  public ExitCommand() {
    super("");
  }

  public boolean execute(TaskList list) {
    System.out.println("Cya!");
    return false;
  }
}
