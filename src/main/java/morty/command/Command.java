package morty.command;

import morty.Storage;
import morty.TaskList;
import morty.Response;

/**
 * Represents a command.
 */
public abstract class Command {

  /**
   * The tokens of the command.
   */
  protected String[] tokens;

  /**
   * True if the command is an exit command, false otherwise.
   */
  protected boolean exit;

  /**
   * Constructs a Command object with the given command and tokens.
   *
   * @param tokens The tokens.
   */
  public Command(String[] tokens) {
    this.tokens = tokens;
  }

  /**
   * Executes the command.
   *
   * @param tasks The list of tasks.
   * @param ui The user interface.
   * @param storage The storage.
   * @return The response after executing the command.
   */
  public abstract String execute(TaskList tasks, Response ui, Storage storage);

  /**
   * Returns true if the command is an exit command, false otherwise.
   *
   * @return True if the command is an exit command, false otherwise.
   */
  public boolean isExit() {
    return this.exit;
  }
}
