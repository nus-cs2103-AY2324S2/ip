package seiki.commands;

import static seiki.common.Messages.MESSAGE_EMPTY_TASKLIST;

import seiki.data.TaskList;
import seiki.data.exception.SeikiException;
import seiki.data.task.Task;
import seiki.storage.Storage;
import seiki.ui.Ui;

/**
 * Represents the 'unmark' command.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    public static final String COMMAND_FORMAT = COMMAND_WORD + " [TASK_NUMBER]";
    public static final String COMMAND_HELPER = "Please use the following format: " + COMMAND_FORMAT;
    public static final String COMMAND_USAGE = COMMAND_WORD
            + ": Unmarks task identified by the task number as completed.\n"
            + "Parameters: TASK_NUMBER (must be positive interger)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final Integer taskNumber;

    public UnmarkCommand(String taskNumber) {
        this.taskNumber = Integer.parseInt(taskNumber);
    }

    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws SeikiException {
        taskList.checkIfListEmpty(MESSAGE_EMPTY_TASKLIST);
        taskList.checkIfNumberValid(taskNumber);

        assert taskNumber >= 0 || taskNumber < taskList.getTaskCount()
                : "Task number should be within range of task list";

        Task task = taskList.getTaskByNumber(taskNumber);
        task.markAsNotDone();
        storage.save(taskList);
        return ui.showUnmarkTask(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
