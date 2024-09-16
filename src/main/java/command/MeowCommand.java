package command;

import dook.Storage;
import task.TaskList;

public class MeowCommand extends Command {

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "meow!";
    }
}
