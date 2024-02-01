package mike.command;

import mike.MikeException;
import mike.TaskList;

public abstract class Command {

    public abstract void execute(TaskList taskList) throws MikeException;

    public abstract boolean isExit();

}
