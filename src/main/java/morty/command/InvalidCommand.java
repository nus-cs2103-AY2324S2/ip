package morty.command;

import morty.Storage;
import morty.TaskList;
import morty.Response;

/**
 * Represents a command that is invalid.
 */
public class InvalidCommand extends Command {

  /**
   * Constructs a InvalidCommand object with the given tokens.
   *
   * @param tokens The tokens of the user input.
   */
  public InvalidCommand(String[] tokens) {
    super(tokens);
  }

  /**
   * Executes the InvalidCommand.
   *
   * @param tasks The list of tasks.
   * @param ui The user interface.
   * @param storage The storage.
   * @return The response after executing the InvalidCommand.
   */
  @Override
  public String execute(TaskList tasks, Response ui, Storage storage) {
    return ui.showInvalidCommand();
  }
}
