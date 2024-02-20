package morty.command;

import morty.Storage;
import morty.TaskList;
import morty.Response;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {

  /**
   * Constructs a ExitCommand object with the given tokens.
   *
   * @param tokens The tokens of the user input.
   */
  public ExitCommand(String[] tokens) {
    super(tokens);
    this.exit = true;
  }

  /**
   * Executes the ExitCommand.
   *
   * @param tasks The list of tasks.
   * @param ui The user interface.
   * @param storage The storage.
   * @return The response after executing the ExitCommand.
   */
  @Override
  public String execute(TaskList tasks, Response ui, Storage storage) {
    return ui.showGoodbye();
  }
}
