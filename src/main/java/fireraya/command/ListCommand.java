package fireraya.command;

import fireraya.main.Storage;
import fireraya.main.TaskList;
import fireraya.main.Ui;

public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.listTasks(tasks.getTasks());
    }

}
