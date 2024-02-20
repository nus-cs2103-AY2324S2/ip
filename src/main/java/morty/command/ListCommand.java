package morty.command;

import morty.Storage;
import morty.TaskList;
import morty.Response;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {

  /**
   * Constructs a ListCommand object with the given tokens.
   *
   * @param tokens The tokens of the user input.
   */
  public ListCommand(String[] tokens) {
    super(tokens);
  }

  /**
   * Executes the ListCommand.
   *
   * @param tasks The list of tasks.
   * @param ui The user interface.
   * @param storage The storage.
   * @return The response after executing the ListCommand.
   */
  @Override
  public String execute(TaskList tasks, Response ui, Storage storage) {
    return ui.showTaskList(tasks);
  }
}
