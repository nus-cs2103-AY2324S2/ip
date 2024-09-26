package damon.command;

import damon.response.Response;
import damon.storage.Storage;
import damon.tasklist.TaskList;
import damon.ui.Ui;

/**
 * Represents DeleteCommand object which is corresponding to user's deleting Task input.
 */
public class DeleteCommand extends Command {
    public DeleteCommand(String command) {
        super(command);
    }


    /**
     * Executes DeleteCommand, i.e., deletes a Task of specific index in current TaskList.
     *
     * @param tasks Current TaskList.
     * @param ui Ui object of Damon object.
     * @param storage Storage object of Damon object.
     * @param response Response object of Damon object.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage, Response response) {
        int index = Integer.parseInt(this.command.split(" ")[1]) - 1;

        ui.showDeleteCommand(index, tasks);
        response.showDeleteCommand(index, tasks);

        tasks.deleteTask(index);
        storage.writeFile(tasks);


    }
}
