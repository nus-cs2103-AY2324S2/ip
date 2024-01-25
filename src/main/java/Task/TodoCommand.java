package Task;

public class TodoCommand extends Command {
  public TodoCommand(String body) {
    super(body);
  }

  public boolean execute(TaskList list) {
    String body = getBody();
    Task task = new Todo(body);
    list.addTask(task);
    System.out.println("Added: " + task + "\nYou now have " + list.size() + " tasks in the list.");
    return true;
  }
}
