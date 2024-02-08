package command;

import dook.Storage;
import dook.Ui;
import task.TaskList;

public class MeowCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "meow!";
    }
}
