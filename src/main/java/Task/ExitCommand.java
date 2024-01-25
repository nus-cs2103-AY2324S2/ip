package Task;

public class ExitCommand extends Command {
  public ExitCommand() {
    super("");
  }

  String execute(TaskList list) {
    return "Cya!";
  }
}
