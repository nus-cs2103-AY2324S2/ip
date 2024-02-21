package seedu.duke.command;


import seedu.duke.common.Messages;
import seedu.duke.common.TaskList;
import seedu.duke.exception.InvalidInputException;
import seedu.duke.storage.Storage;
import seedu.duke.task.Task;
import seedu.duke.ui.Ui;

/**
 * Represents a delete command initiated by the user. <code>DeleteCommand</code> would delete a task in the task list
 * with the corresponding number.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String COMMAND_USAGE = "delete: delete a task on the list.\n"
            + "Example: delete 1";
    private int taskNumber;

    /**
     * Constructor of the DeleteCommand
     *
     * @param taskNumber The task number in the list to be deleted
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Deletes a task in the list with corresponding list number
     * @throws InvalidInputException if the task number given is out of the list
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidInputException {
        if (taskNumber <= 0 || taskNumber > taskList.getListSize()) {
            throw new InvalidInputException(
                    String.format(Messages.MESSAGE_INVALID_INPUT_VALUE, "you can only mark task that are listed"));
        }


        Task task = taskList.getTask(taskNumber - 1);
        taskList.deleteTask(taskNumber - 1);
        ui.generateDeleteTaskResponse(task, taskList);
    }
}
