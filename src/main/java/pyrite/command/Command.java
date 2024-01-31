package pyrite.command;

import pyrite.StateFile;
import pyrite.TaskList;

public abstract class Command {
    public abstract String execute(TaskList tasks, StateFile file);
}
