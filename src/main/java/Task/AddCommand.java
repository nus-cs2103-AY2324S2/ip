package Task;

public class AddCommand extends Command {
  public AddCommand(String body) {
    super(body);
  }

  String execute(TaskList list) {
    String body = getBody();
    Task task = new Task(body);
    list.addTask(task);
    return "Added: " + task + "\nYou now have " + list.size() + " tasks in the list.";
  }
}
