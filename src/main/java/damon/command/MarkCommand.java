package damon.command;

import damon.storage.Storage;
import damon.tasklist.TaskList;
import damon.ui.Ui;

public class MarkCommand extends Command {
    public MarkCommand(String command) {
        super(command);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int index = Integer.parseInt(this.command.split(" ")[1]) - 1;

        tasks.markTask(index);
        ui.showMarkCommand(tasks, index);
        storage.writeFile(tasks);
    }
}
