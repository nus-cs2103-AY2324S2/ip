package morty.command;

import morty.Storage;
import morty.TaskList;
import morty.MortyException;
import morty.Response;

public class ArchiveCommand extends Command {

  public ArchiveCommand(String[] tokens) {
    super(tokens);
  }

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
