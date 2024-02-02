package duke.command;

import duke.TaskList;

import duke.Storage;
import duke.Ui;

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
   * @param tasks   The list of tasks.
   * @param ui      The user interface.
   * @param storage The storage.
   */
  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) {
    ui.showInvalidCommand();
  }
}
