package duke.command;


import duke.common.Messages;
import duke.common.TaskList;
import duke.exception.InvalidInputException;
import duke.task.Task;
import duke.storage.Storage;
import duke.ui.Ui;

import java.util.List;

/**
 * Represents an unmark command initiated by the user. <Code>UnmarkCommand</Code> corresponds to a command that
 * user would mark a task as not done.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    public static final String COMMAND_USAGE = "unmark: marks a task in the list as uncompleted by taking a numerical value input.\n" +
            "Example mark 1";
    private int taskNumber;

    /**
     * Constructor of the unmark command
     * @param taskNumber the task number in the list that would mark as not done
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Marks the task with corresponds number in the list to be not done and show the result using ui.
     * @param taskList
     * @param ui
     * @param storage
     * @throws InvalidInputException If user keys in number that is not in the range of the list
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidInputException {
        if (taskNumber <= 0 || taskNumber > taskList.getListSize()) {
            throw new InvalidInputException(
                    String.format(Messages.MESSAGE_INVALID_INPUT_VALUE, "you can only mark task that are listed"));
        }
        Task task = taskList.getTask(taskNumber - 1);
        task.setHasDone(false);
        ui.showMarkAsNotDone(task);
    }
}
