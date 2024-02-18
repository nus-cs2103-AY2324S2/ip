package bmo.command;

import bmo.task.Task;
import bmo.ui.Ui;
import bmo.util.Storage;
import bmo.util.TaskList;

/**
 * Represents a done command to be executed.
 */
public class DoneCommand extends Command {

    private final String index;

    /**
     * Constructor for a done command.
     *
     * @param index The index of the task to be marked as done.
     */
    public DoneCommand(String index) {
        this.index = index;
    }

    /**
     * Executes the done command.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.index.isBlank() || !this.index.matches("\\d+")) {
            ui.printErrInvalidIndex();
            return;
        }

        int idx = Integer.parseInt((index));

        if (idx > tasks.size() || idx <= 0) {
            ui.printErrInvalidIndex();
            return;
        }

        Task currTask = tasks.get(idx - 1);

        if (currTask.getStatus()) {
            ui.printErrUselessCommand();
            return;
        }

        currTask.setStatus(true);
        ui.printDoneTask(tasks, idx);
    }
}
