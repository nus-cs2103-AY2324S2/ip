package seedu.duke.command;

import seedu.duke.common.Messages;
import seedu.duke.common.TaskList;
import seedu.duke.exception.InvalidInputException;
import seedu.duke.storage.Storage;
import seedu.duke.task.Task;
import seedu.duke.ui.Ui;

/**
 * Represents a mark command initiated by the user. <code>MarkCommand</code> would mark a task as done
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    public static final String COMMAND_USAGE = "mark: marks a task in the list as completed by taking "
            + "a numerical value input.\n" + "Example mark 1";
    private int taskNumber;

    /**
     * Constructor of the MarkCommand
     *
     * @param taskNumber The task number in the list to be marked as done
     */
    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Marks the selected task as done and show the result to the user using ui
     * @throws InvalidInputException If user keyed in task number out of range
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidInputException {
        if (taskNumber <= 0 || taskNumber > taskList.getListSize()) {
            throw new InvalidInputException(
                    String.format(Messages.MESSAGE_INVALID_INPUT_VALUE, "you can only mark task that are listed"));
        }


        Task task = taskList.getTask(taskNumber - 1);
        task.setHasDone(true);
        ui.generateMarkAsDoneResponse(task);
    }
}
