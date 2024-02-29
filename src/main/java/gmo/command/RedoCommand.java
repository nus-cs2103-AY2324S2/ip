package gmo.command;

import gmo.task.Task;
import gmo.ui.Ui;
import gmo.util.Storage;
import gmo.util.TaskList;

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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.index.isBlank() || !this.index.matches("\\d+")) {
            return ui.printErrInvalidIndex();
        }

        int idx = Integer.parseInt((index));

        if (idx > tasks.size() || idx <= 0) {
            return ui.printErrInvalidIndex();
        }

        Task currTask = tasks.get(idx - 1);

        if (!currTask.getStatus()) {
            return ui.printErrUselessCommand();
        }

        currTask.setStatus(false);
        return ui.printRedoTask(tasks, idx);
    }
}
