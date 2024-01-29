package commands;

import exception.TodoFormatException;
import exception.DeadlineFormatException;
import exception.UncleBobException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

abstract public class Command {
    protected TaskList tasks;
    protected Storage storage;
    protected Ui ui;

    public Command() {};

    abstract public void execute(TaskList tasks, Storage storage, Ui ui) throws UncleBobException;

    public static boolean isExit(Command c) {
        return c instanceof ExitCommand; // instanceof returns false if it is null
    }
}
