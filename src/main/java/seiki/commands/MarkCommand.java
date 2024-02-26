package seiki.commands;

import static seiki.common.Messages.MESSAGE_EMPTY_TASKLIST;

import seiki.data.TaskList;
import seiki.data.exception.SeikiException;
import seiki.data.task.Task;
import seiki.storage.Storage;
import seiki.ui.Ui;

/**
 * Represents the 'mark' command.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_HELPER = "Please follow the format: mark [task number]";
    public static final String COMMAND_WORD = "mark";
    private final Integer taskNumber;

    public MarkCommand(String taskNumber) {
        this.taskNumber = Integer.parseInt(taskNumber);
    }

    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws SeikiException {
        taskList.checkIfListEmpty(MESSAGE_EMPTY_TASKLIST);
        taskList.checkIfNumberValid(taskNumber);

        assert taskNumber >= 0 || taskNumber < taskList.getTaskCount() : "Task number should be within range of task list";

        Task task = taskList.getTaskByNumber(taskNumber);
        task.markAsDone();
        storage.save(taskList);
        return ui.showMarkTask(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
