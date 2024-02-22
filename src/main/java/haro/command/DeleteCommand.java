package haro.command;

import haro.Storage;
import haro.TaskList;
import haro.Ui;
import haro.exception.InvalidArgsException;
import haro.task.Task;

/**
 * The DeleteCommand class represents a command to delete a task from the TaskList.
 * It extends the base Command class and implements the execute method to delete the task.
 */
public class DeleteCommand extends Command {
    private int taskNumber;

    /**
     * Constructs a DeleteCommand instance with the specified task index to delete from the TaskList.
     * @param taskNumber Index of the task in the TaskList to be deleted
     */
    public DeleteCommand(int taskNumber) {
        super(false);
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the command by deleting the Task with the specified task index from the TaskList and
     * displays a corresponding message.
     *
     * @param taskList TaskList to be modified by the command
     * @param ui       Ui for displaying messages related to command execution
     * @param storage  Storage for saving and loading task data
     * @throws InvalidArgsException If the provided task index number is invalid
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidArgsException {
        Task currTask = taskList.deleteTask(this.taskNumber);
        return ui.printDeleteTask(currTask, taskList.getSize());
    }
}
