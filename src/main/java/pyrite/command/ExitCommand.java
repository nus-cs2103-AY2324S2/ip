package pyrite.command;

import pyrite.StateFile;
import pyrite.TaskList;

public class ExitCommand extends Command{
    @Override
    public String execute(TaskList tasks, StateFile file) {
        return "Bye. Hope to see you again soon!";
    }
}
