package gmo.command;

import gmo.ui.Ui;
import gmo.util.Storage;
import gmo.util.TaskList;

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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.index.isBlank() || !this.index.matches("\\d+")) {
            return ui.printErrInvalidIndex();
        }

        int idx = Integer.parseInt((index));

        if (idx > tasks.size() || idx <= 0) {
            return ui.printErrInvalidIndex();
        }

        String taskDesc = tasks.get(idx - 1).toString();
        tasks.remove(idx - 1);
        return ui.printDeleteTask(taskDesc, idx);
    }
}
