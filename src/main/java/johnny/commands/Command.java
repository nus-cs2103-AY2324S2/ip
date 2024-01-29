package johnny.commands;

import johnny.exceptions.JohnnyException;
import johnny.storage.Storage;
import johnny.ui.Ui;
import johnny.tasks.TaskList;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws JohnnyException;
    public abstract boolean isExit();

}
