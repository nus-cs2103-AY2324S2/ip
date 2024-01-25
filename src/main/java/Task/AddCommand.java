package Task;

public class AddCommand extends Command {
  public AddCommand(String body) {
    super(body);
  }

  public boolean execute(TaskList list) throws DukeException {
    String body = getBody();
    Task task = new Task(body);
    list.addTask(task);
    System.out.println("Added: " + task + "\nYou now have " + list.size() + " tasks in the list.");
    return true;
  }
}
