package morty.command;

import morty.Storage;
import morty.TaskList;
import morty.MortyException;
import morty.Response;

public class ArchiveCommand extends Command {

  /**
   * Constructor for ArchiveCommand.
   *
   * @param tokens The tokens of the command.
   */
  public ArchiveCommand(String[] tokens) {
    super(tokens);
  }

  /**
   * Executes the archive command.
   *
   * @param tasks The task list.
   * @param ui The UI of Morty.
   * @param storage The storage of Morty.
   * @return The response of Morty.
   */
  @Override
  public String execute(TaskList tasks, Response ui, Storage storage) {
    try {
      storage.archive();
      return ui.showTasksArchived();
    } catch (MortyException e) {
      return ui.showErrorMessage(e.getMessage());
    }
  }
}
