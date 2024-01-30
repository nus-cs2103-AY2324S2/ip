package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class MarkCommand extends Command {
    public MarkCommand(String keyword, String parameters) {
        super(keyword, parameters);
    }

    /**
     * @param storage
     * @param ui
     * @param taskList
     */
    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) {
        try {
            Task task = taskList.markTaskAsDone(parameters);
            ui.showMarkedTask(task);
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
    }
}
