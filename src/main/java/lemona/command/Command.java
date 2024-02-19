package lemona.command;

import lemona.oop.TaskList;

public abstract class Command {
    public abstract String execute(TaskList tasks);
}
