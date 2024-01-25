package Task;

public class TodoCommand extends Command {
  public TodoCommand(String body) {
    super(body);
  }

  public boolean execute(TaskList list) throws DukeException {
    String body = getBody();
    if (body.isEmpty()) {
      throw new DukeException("The description of a todo cannot be empty.",
          "Sorry, you need to tell me what you want to add. I can't add empty tasks.");
    }
    Task task = new Todo(body);
    list.addTask(task);
    System.out.println("Added: " + task + "\nYou now have " + list.size() + " tasks in the list.");
    return true;
  }
}
