package damon.command;

import damon.storage.Storage;
import damon.tasklist.TaskList;
import damon.ui.Ui;

public class DeleteCommand extends Command {
    public DeleteCommand(String command) {
        super(command);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int index = Integer.parseInt(this.command.split(" ")[1]) - 1;

        ui.showDeleteCommand(index, tasks);
        tasks.deleteTask(index);
        storage.writeFile(tasks);
    }
}
