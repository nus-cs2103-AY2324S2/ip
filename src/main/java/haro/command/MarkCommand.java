package haro.command;

import haro.Storage;
import haro.TaskList;
import haro.Ui;
import haro.exception.InvalidArgsException;
import haro.task.Task;

/**
 * The MarkCommand class represents a command to mark a task as done in the TaskList.
 * It extends the base Command class and implements the execute method to mark the task.
 */
public class MarkCommand extends Command {
    private int taskNumber;

    /**
     * Constructs a MarkCommand instance with the specified task number.
     *
     * @param taskNumber Task number of the task to be marked in the TaskList
     */
    public MarkCommand(int taskNumber) {
        super(false);
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the command by marking the specified task as done in the TaskList and
     * displaying a corresponding message.
     *
     * @param taskList TaskList to be modified by the command
     * @param ui       Ui for displaying messages related to command execution
     * @param storage  Storage for saving and loading task data
     * @throws InvalidArgsException If the provided task number is invalid
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidArgsException {
        Task currTask = taskList.markTask(this.taskNumber);
        return ui.printMarkTask(currTask);
    }

    /**
     * Checks if the given object is equal to this MarkCommand.
     *
     * @param o Object to be compared
     * @return true if the object is an instance of MarkCommand with the same task number; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (o instanceof MarkCommand) {
            MarkCommand oMarkCommand = (MarkCommand) o;

            if (oMarkCommand.taskNumber == this.taskNumber) {
                return true;
            }
        }
        return false;
    }
}
