package haro.command;

import haro.Storage;
import haro.TaskList;
import haro.Ui;
import haro.exception.InvalidArgsException;
import haro.task.Task;

/**
 * The UnmarkCommand class represents a command to unmark a task in the TaskList.
 * It extends the base Command class and implements the execute method to unmark the task.
 */
public class UnmarkCommand extends Command {
    private int taskNumber;

    /**
     * Constructs an UnmarkCommand instance with the specified task number.
     *
     * @param taskNumber Task number of the task to be unmarked in the TaskList
     */
    public UnmarkCommand(int taskNumber) {
        super(false);
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the command by unmarking the specified task in the TaskList and displaying a corresponding message.
     *
     * @param taskList TaskList to be modified by the command
     * @param ui       Ui for displaying messages related to command execution
     * @param storage  Storage for saving and loading task data
     * @throws InvalidArgsException If the provided task number is invalid
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidArgsException {
        Task currTask = taskList.unmarkTask(this.taskNumber);
        return ui.printUnmarkTask(currTask);
    }

}
