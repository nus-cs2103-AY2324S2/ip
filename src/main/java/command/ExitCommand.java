package command;

import duke.Storage;
import duke.TaskList;

public class ExitCommand extends Command {
    @Override
    public void execute(Storage storage, TaskList taskList) {
        System.out.println("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
