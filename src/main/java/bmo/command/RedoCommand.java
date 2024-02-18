package bmo.command;

import bmo.util.Storage;
import bmo.util.TaskList;
import bmo.ui.Ui;
import bmo.task.Task;

/**
 * Represents a redo command to be executed.
 */
public class RedoCommand extends Command {

    private final String index;

    /**
     * Constructor for a redo command.
     *
     * @param index The index of the task to be redone.
     */
    public RedoCommand(String index) {
        this.index = index;
    }

    /**
     * Executes the redo command.
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

        if (!currTask.getStatus()) {
            ui.printErrUselessCommand();
            return;
        }

        currTask.setStatus(false);
        ui.printRedoTask(tasks, idx);
    }
}
