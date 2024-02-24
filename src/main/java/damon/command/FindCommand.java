package damon.command;

import damon.storage.Storage;
import damon.tasklist.TaskList;
import damon.ui.Ui;

public class FindCommand extends Command {
    public FindCommand(String command) {
        super(command);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String keyword = this.command.split(" ")[1];

        TaskList serchedTaskList = tasks.findTasks(keyword);
        ui.showFindCommand(serchedTaskList);
    }
}