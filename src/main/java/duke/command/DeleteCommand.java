package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A command that deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Construct a command that deletes a task at the given index.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.deleteTask(index);
        } catch (TaskList.TaskNotFound e) {
            ui.showError(e);
        }
    }
}
