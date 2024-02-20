package adam.command;

import adam.AdamException;
import adam.Storage;
import adam.task.TaskList;
import adam.ui.Ui;

/**
 * {@inheritDoc}
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    int taskNumber;

    /**
     * Returns a command to mark a task as done.
     *
     * @param taskNumber Index of the task to mark as done.
     */
    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * {@inheritDoc}
     * Marks the task at the provided index as done in the list of tasks.
     *
     * @param taskList Current TaskList of program.
     * @param ui Ui used by the program.
     * @param storage Storage used by the program.
     * @return The result of the command executed to be printed as the program's response.
     * @throws AdamException If command cannot be executed.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws AdamException {
        String t = taskList.mark(taskNumber);
        return  ui.showResult(
                "Nice, I've marked this task as done:",
                t);
    }

    /**
     * {@inheritDoc}
     *
     * @return True if program will exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
