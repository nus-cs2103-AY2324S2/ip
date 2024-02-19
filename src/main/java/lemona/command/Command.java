package lemona.command;

import lemona.oop.TaskList;
import lemona.oop.Ui;
import lemona.oop.Storage;

public abstract class Command {
    public abstract String execute(TaskList tasks);
}
