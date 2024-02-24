package damon.command;

import damon.storage.Storage;
import damon.tasklist.TaskList;
import damon.ui.Ui;

/**
 * Represents MarkCommand object which is corresponding to
 * user's input for un-marking a specific Task to not done status.
 */
public class UnMarkCommand extends Command {

    public UnMarkCommand(String command) {
        super(command);
    }

    /**
     * Executes UnMarkCommand,
     * i.e., un-marks a Task of specific index in current TaskList to not done status.
     *
     * @param tasks Current TaskList.
     * @param ui Ui object of Damon object.
     * @param storage Storage object of Damon object.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int index = Integer.parseInt(this.command.split(" ")[1]) - 1;

        tasks.unMarkTask(index);
        ui.showUnMarkCommand(tasks, index);
        storage.writeFile(tasks);
    }
}
