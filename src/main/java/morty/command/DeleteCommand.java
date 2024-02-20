package morty.command;

import morty.Storage;
import morty.TaskList;
import morty.Response;
import morty.task.Task;

/**
 * Represents a command to add a deadline task.
 */
public class DeleteCommand extends Command {

  /**
   * Constructs a DeleteCommand object with the given tokens.
   *
   * @param tokens The tokens of the user input.
   */
  public DeleteCommand(String[] tokens) {
    super(tokens);
  }

  /**
   * Executes the DeleteCommand.
   *
   * @param tasks The list of tasks.
   * @param ui The user interface.
   * @param storage The storage.
   * @return The response after executing the DeleteCommand.
   */
  public String execute(TaskList tasks, Response ui, Storage storage) {
    try {
      int index = Integer.parseInt(tokens[1]) - 1;
      Task task = tasks.get(index);
      tasks.remove(index);
      storage.save(tasks);
      return ui.showTaskRemoved(task, tasks.getSize());
    } catch (NumberFormatException e) {
      return ui.showInvalidDelete();
    } catch (IndexOutOfBoundsException e) {
      return ui.showInvalidDelete();
    }
  }
}
