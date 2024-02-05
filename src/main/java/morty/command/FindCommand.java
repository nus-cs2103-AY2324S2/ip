package morty.command;

import morty.Storage;
import morty.TaskList;
import morty.Ui;

/**
 * Represents a command to find tasks.
 */
public class FindCommand extends Command {

  /**
   * Constructs a FindCommand object with the given tokens.
   *
   * @param tokens The tokens.
   */
  public FindCommand(String[] tokens) {
    super(tokens);
  }

  /**
   * Executes the command to find tasks.
   *
   * @param tasks   The list of tasks.
   * @param ui      The user interface.
   * @param storage The storage.
   */
  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) {
    try {
      TaskList matchingTasks = new TaskList(tasks.find(this.tokens[1]));
      ui.showTaskList(matchingTasks);
    } catch (ArrayIndexOutOfBoundsException e) {
      ui.showFindUsage();
    }
  }

}
