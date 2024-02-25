package seiki.commands;

import java.util.ArrayList;

import seiki.data.TaskList;
import seiki.data.exception.SeikiException;
import seiki.data.task.Task;
import seiki.storage.Storage;
import seiki.ui.Ui;

/**
 * Represents the 'mark' command.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    private ArrayList<String> args;

    public MarkCommand(ArrayList<String> args) {
        this.args = args;
    }

    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws SeikiException {
        if (taskList.getTaskCount() == 0) {
            throw new SeikiException("There are currently no tasks to be marked.");
        } else {
            int taskNumber = Integer.parseInt(args.get(0));
            if (taskNumber < 1 || taskNumber > taskList.getTaskCount()) {
                throw new SeikiException("Please enter a valid task number.");
            } else {
                assert taskNumber >= 0 || taskNumber < taskList.getTaskCount() : "Task number should be within range of task list";
                Task task = taskList.getTaskByNumber(taskNumber);
                task.markAsDone();
                storage.save(taskList);
                return ui.showMarkTask(task);
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
