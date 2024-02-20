package morty.command;

import morty.Storage;
import morty.TaskList;
import morty.Response;
import morty.task.Task;

/**
 * Represents a command to add a deadline task.
 */
public class DoneCommand extends Command {

  /**
   * Constructs a DoneCommand object with the given tokens.
   *
   * @param tokens The tokens of the user input.
   */
  public DoneCommand(String[] tokens) {
    super(tokens);
  }

  /**
   * Executes the DoneCommand.
   *
   * @param tasks The list of tasks.
   * @param ui The user interface.
   * @param storage The storage.
   * @return The response after executing the DoneCommand.
   */
  @Override
  public String execute(TaskList tasks, Response ui, Storage storage) {
    try {
      int index = Integer.parseInt(tokens[1]) - 1;
      Task task = tasks.get(index);
      tasks.markDone(index);
      storage.save(tasks);
      return ui.showTaskMarkedDone(task);
    } catch (NumberFormatException e) {
      return ui.showInvalidDone();
    } catch (IndexOutOfBoundsException e) {
      return ui.showInvalidDone();
    }
  }
}
