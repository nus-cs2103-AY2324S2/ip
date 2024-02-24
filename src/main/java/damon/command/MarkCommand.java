package damon.command;

import damon.storage.Storage;
import damon.tasklist.TaskList;
import damon.ui.Ui;

/**
 * Represents MarkCommand object which is corresponding to
 * user's input for marking a specific Task as done status.
 */
public class MarkCommand extends Command {
    public MarkCommand(String command) {
        super(command);
    }

    /**
     * Executes MarkCommand,
     * i.e., marks a Task of specific index in current TaskList as done status.
     *
     * @param tasks Current TaskList.
     * @param ui Ui object of Damon object.
     * @param storage Storage object of Damon object.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int index = Integer.parseInt(this.command.split(" ")[1]) - 1;
        tasks.markTask(index);
        ui.showMarkTask(tasks, index);
        storage.writeFile(tasks);
    }
}
