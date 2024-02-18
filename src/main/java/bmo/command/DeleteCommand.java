package bmo.command;

import bmo.util.Storage;
import bmo.util.TaskList;
import bmo.ui.Ui;

/**
 * Represents a delete command to be executed.
 */
public class DeleteCommand extends Command {

    private final String index;

    /**
     * Constructor for a delete command.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(String index) {
        this.index = index;
    }

    /**
     * Executes the delete command.
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

        String taskDesc = tasks.get(idx - 1).toString();
        tasks.remove(idx - 1);
        ui.printDeleteTask(taskDesc, idx);
    }
}
