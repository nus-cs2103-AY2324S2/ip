package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class UnmarkCommand extends Command {
    public UnmarkCommand(String keyword, String parameters) {
        super(keyword, parameters);
    }

    /**
     * @param storage
     * @param ui
     * @param taskList
     */
    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList)  {
        try {
            Task task = taskList.markTaskAsUndone(parameters);
            ui.showUnmarkedTask(task);
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
    }
}
