package Task;

public class MarkCommand extends Command {
  public MarkCommand(String body) {
    super(body);
  }

  public boolean execute(TaskList list) throws DukeException {
    String body = getBody();
    if (body.isEmpty()) {
      throw new InvalidTaskIndexException("The index of a task cannot be empty.",
          "Sorry, but I don't know which task you want to mark as done.");
    }
    int index = Integer.parseInt(body);
    if (index < 1 || index > list.size()) {
      throw new InvalidTaskIndexException(
          "The index of a task cannot be less than 1 or greater than the number of tasks.",
          "Sorry, but task number " + index + " does not exist. You only have " + list.size() + " tasks.");
    }
    list.markTaskAsDone(index);
    System.out.println("Done: " + list.get(index - 1));
    return true;
  }
}
