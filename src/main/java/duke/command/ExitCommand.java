package duke.command;

import duke.Storage;
import duke.TaskList;

public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks, Storage storage) {
        this.isExit = true;
        return "Bye!";
    }
}
