package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * @inheritDoc
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    int taskNumber;

    /**
     * Returns a command to mark a task as done.
     *
     * @param taskNumber Index of the task to mark as done
     */
    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Marks the task at the provided index as done in the list of tasks.
     * @inheritDoc
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String t = tasks.mark(this.taskNumber);
        ui.showResult("Nice, I've marked this duke.task as done:");
        ui.showResult(t);
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
