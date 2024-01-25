package Task;

public class TodoCommand extends Command {
  public TodoCommand(String body) {
    super(body);
  }

  String execute(TaskList list) {
    String body = getBody();
    Task task = new Todo(body);
    list.addTask(task);
    return "Added: " + task + "\nYou now have " + list.size() + " tasks in the list.";
  }
}
