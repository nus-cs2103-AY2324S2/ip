package Task;

public class DeleteCommand extends Command {
  public DeleteCommand(String body) {
    super(body);
  }

  public boolean execute(TaskList list) throws DukeException {
    String body = getBody();
    if (body.isEmpty()) {
      throw new EmptyTaskDescriptionException("The description of a delete cannot be empty.",
          "Sorry, you need to tell me what you want to delete. I can't delete nothing.");
    }
    int index = Integer.parseInt(body);
    if (index < 1 || index > list.size()) {
      throw new InvalidTaskIndexException(
          "The index of a task cannot be less than 1 or greater than the number of tasks.",
          "Sorry, but task number " + index + " does not exist. You only have " + list.size() + " tasks.");
    }
    Task task = list.deleteTask(index);
    System.out.println("Deleted: " + task + "\nYou now have " + list.size() + " tasks in the list.");
    return true;
  }
}
