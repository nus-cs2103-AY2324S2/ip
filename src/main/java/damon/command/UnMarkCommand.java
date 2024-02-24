package damon.command;

import damon.storage.Storage;
import damon.tasklist.TaskList;
import damon.ui.Ui;

public class UnMarkCommand extends Command {
    public UnMarkCommand(String command) {
        super(command);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int index = Integer.parseInt(this.command.split(" ")[1]) - 1;

        tasks.unMarkTask(index);
        ui.showUnMarkCommand(tasks, index);
        storage.writeFile(tasks);
    }
}
