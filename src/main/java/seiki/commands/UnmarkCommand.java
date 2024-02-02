package seiki.commands;

import java.util.ArrayList;

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
    private ArrayList<String> args;

    public UnmarkCommand(ArrayList<String> args) {
        this.args = args;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws SeikiException {
        if (taskList.getTaskCount() == 0) {
            throw new SeikiException("There are currently no tasks to be unmarked.");
        } else {
            int taskNumber = Integer.parseInt(this.args.get(0));
            if (taskNumber < 1 || taskNumber > taskList.getTaskCount()) {
                throw new SeikiException("Please enter a valid task number.");
            } else {
                Task task = taskList.getTaskByNumber(taskNumber);
                task.markAsNotDone();
                storage.save(taskList);
                ui.showUnmarkTask(task);
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
